package ru.mygames.classicsnake.ui.screens.game

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.mygames.classicsnake.data.local.datastore.DataStoreManager
import ru.mygames.classicsnake.data.local.datastore.GameBoardSize
import ru.mygames.classicsnake.data.local.datastore.GameLevel
import ru.mygames.classicsnake.data.local.datastore.GameRules
import ru.mygames.classicsnake.domain.models.BonusItem
import ru.mygames.classicsnake.domain.models.BonusType
import ru.mygames.classicsnake.domain.models.CollisionStatus
import ru.mygames.classicsnake.domain.models.Direction
import ru.mygames.classicsnake.domain.models.GameLevelConfig
import ru.mygames.classicsnake.domain.models.GameStatus
import ru.mygames.classicsnake.domain.models.Point
import ru.mygames.classicsnake.domain.models.UserGameResult
import ru.mygames.classicsnake.domain.models.toDto
import ru.mygames.classicsnake.domain.repository.SnakeController
import ru.mygames.classicsnake.ui.base.architecture.BaseViewModel
import ru.mygames.classicsnake.ui.screens.game.models.GameAction
import ru.mygames.classicsnake.ui.screens.game.models.GameEvent
import ru.mygames.classicsnake.ui.screens.game.models.GameViewState
import kotlin.random.Random

class GameViewModel(
    private val dataStoreManager: DataStoreManager,
    private val snakeController: SnakeController
) : BaseViewModel<GameEvent, GameViewState, GameAction>(initialState = GameViewState.Loading) {

    override fun obtainEvent(event: GameEvent) {
        when(val state = viewState) {
            is GameViewState.Loading -> reduce(state, event)
            is GameViewState.Display -> reduce(state, event)
        }
    }

    private fun reduce(state: GameViewState.Loading, event: GameEvent) {
        when (event) {
            is GameEvent.EnterScreen -> loadGame()
            else -> {}
        }
    }

    private fun reduce(state: GameViewState.Display, event: GameEvent) {
        when (event) {
            is GameEvent.ChangeSnakeDirection -> changeSnakeDirection(state, event.newDirection)
            else -> {}
        }
    }

    private fun loadGame() {
        viewModelScope.launch {
            while (true) {
                when (val state = viewState) {
                    GameViewState.Loading -> {
                        val appSettings = dataStoreManager.appSettings.first()

                        val gameLevelConfig = when(appSettings.gameLevel) {
                            GameLevel.Easy -> GameLevelConfig.Easy
                            GameLevel.Normal -> GameLevelConfig.Medium
                            GameLevel.Hard -> GameLevelConfig.Hard
                        }

                        viewState = GameViewState.Display(
                            snake = snakeController.spawn(
                                Point(
                                    appSettings.boardSize.columns / 2,
                                    appSettings.boardSize.rows / 2
                                )
                            ),
                            currentSnakeLives =  gameLevelConfig.startSnakeLives,
                            maxSnakeLives = gameLevelConfig.maxSnakeLives,
                            bonusItems = listOf(),
                            blockItems = listOf(),
                            gameStatus = GameStatus.Playing,
                            boardSize = appSettings.boardSize,
                            time = 0L,
                            sessionTime = convertTimeToString(0L),
                            score = 0,
                            gameRules = appSettings.gameRules,
                            gameLevelConfig = gameLevelConfig
                        )
                    }

                    is GameViewState.Display -> {
                        val bonusItems = state.bonusItems.toMutableList()
                        val blockItems = state.blockItems.toMutableList()
                        var snake = state.snake
                        var currentSnakeLives = state.currentSnakeLives
                        var gameStatus = state.gameStatus
                        var score = state.score
                        var time = state.time

                        if (gameStatus == GameStatus.Stopped) {
                            delay(state.gameLevelConfig.snakeSpeed)
                            continue
                        }

                        val snakeCollisionStatus = snakeController.checkCollision(
                                state.snake,
                                state.boardSize,
                                state.bonusItems,
                                state.blockItems
                            )

                        when (snakeCollisionStatus) {
                            is CollisionStatus.Board -> {
                                if (state.gameRules.damageColliderEnabled) {
                                    currentSnakeLives--
                                }
                                snake = snakeController.discard(snake, 1)
                                gameStatus = GameStatus.Stopped
                            }

                            is CollisionStatus.Snake -> {
                                if (state.gameRules.throwTailEnabled) {
                                    snake = snakeController.throwTail(snake, snakeCollisionStatus.point)
                                } else {
                                    currentSnakeLives--
                                }
                            }

                            is CollisionStatus.BonusItem -> {
                                snake = snakeController.grow(snake)
                                score++
                                bonusItems.removeAt(bonusItems.lastIndex)
                            }

                            is CollisionStatus.BlockItem -> {
                                if (state.gameRules.damageColliderEnabled) {
                                    currentSnakeLives--
                                }
                                snake = snakeController.discard(snake, 1)
                                gameStatus = GameStatus.Stopped
                            }

                            is CollisionStatus.None -> {
                                snake = snakeController.move(snake)
                            }
                        }

                        if(bonusItems.isEmpty()) {
                            val bonusItem = generateBonusItem(state)
                            bonusItems.add(bonusItem)
                        }

                        if(state.gameRules.randomWallsEnabled) {
                            if(state.time % state.gameLevelConfig.blockItemRespawnTime == 0L) {
                                val blockItem = generateBlockItem(state)
                                blockItems.add(
                                    blockItem
                                )
                            }
                        }

                        if(currentSnakeLives <= 0) {
                            viewState = state.copy(
                                gameStatus = GameStatus.Finished
                            )
                            dataStoreManager.saveGameResult(
                                UserGameResult(
                                    score = score,
                                    time = state.sessionTime
                                ).toDto()
                            )
                            sideEffect(GameAction.CloseScreen)
                            break
                        }

                        time += state.gameLevelConfig.snakeSpeed

                        viewState =
                            state.copy(
                                snake = snake,
                                currentSnakeLives = currentSnakeLives,
                                bonusItems = bonusItems,
                                blockItems = blockItems,
                                score = score,
                                time = time,
                                sessionTime = convertTimeToString(time),
                                gameStatus = gameStatus
                            )

                        delay(state.gameLevelConfig.snakeSpeed)
                    }
                }
            }
        }
    }

    private fun changeSnakeDirection(
        state: GameViewState.Display,
        direction: Direction
    ) {
        viewModelScope.launch {
            val snake = snakeController.rotate(snake = state.snake, direction = direction)

            val gameStatus = if(state.gameStatus == GameStatus.Stopped) {
                GameStatus.Playing
            } else {
                state.gameStatus
            }

            viewState = state.copy(snake = snake, gameStatus = gameStatus)
        }
    }

    private fun convertTimeToString(time: Long): String {
        val allSeconds = time/1000
        val minutes = allSeconds/60
        val seconds = allSeconds % 60

        val minutesStr = if(minutes < 10) "0$minutes" else "$minutes"
        val secondsStr = if(seconds < 10) "0$seconds" else "$seconds"

        return "$minutesStr:$secondsStr"
    }

    private fun findEmptyGameBoardCell(state: GameViewState.Display): Point {
        var randomRow: Int
        var randomColumn: Int

        do {
            randomRow = Random.nextInt(0, state.boardSize.rows)
            randomColumn = Random.nextInt(0, state.boardSize.columns)
        } while (
            state.blockItems.any { it.x != randomRow && it.y != randomColumn} ||
            state.snake.body.any { it.x != randomRow && it.y != randomColumn} ||
            state.bonusItems.any { it.position.x != randomRow && it.position.y != randomColumn}
        )

        return Point(randomRow, randomColumn)
    }

    private fun generateBonusItem(state: GameViewState.Display): BonusItem {
        //val freePoint = findEmptyGameBoardCell(state)
        return BonusItem(
            position = findEmptyGameBoardCell(state),
            status = BonusType.IncreaseScore
        )
    }

    private fun generateBlockItem(state: GameViewState.Display): Point {
        return findEmptyGameBoardCell(state)
    }
}