package ru.mygames.classicsnake.ui.screens.game.views

import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mygames.classicsnake.data.local.datastore.GameBoardSize
import ru.mygames.classicsnake.data.local.datastore.GameRules
import ru.mygames.classicsnake.domain.models.BonusItem
import ru.mygames.classicsnake.domain.models.BonusType
import ru.mygames.classicsnake.domain.models.Direction
import ru.mygames.classicsnake.domain.models.GameLevelConfig
import ru.mygames.classicsnake.domain.models.GameStatus
import ru.mygames.classicsnake.domain.models.Point
import ru.mygames.classicsnake.domain.models.Snake
import ru.mygames.classicsnake.ui.screens.game.models.GameEvent
import ru.mygames.classicsnake.ui.screens.game.models.GameViewState
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme

@Composable
fun GameViewDisplay(
    state: GameViewState.Display,
    dispatcher: (GameEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        //Spacer(modifier = Modifier.weight(1f))

        GameTopBar(
            sessionTime = state.sessionTime,
            score = state.score,
            currentSnakeLives = state.currentSnakeLives,
            maxSnakeLives = state.maxSnakeLives,
            modifier = Modifier.fillMaxWidth()
        )

        //Spacer(modifier = Modifier.height(16.dp))

        GameBoard(
            snake = state.snake,
            bonusItems = state.bonusItems,
            blockItems = state.blockItems,
            boardSize = state.boardSize
        )

        GameBottomBar(
            modifier = Modifier.fillMaxWidth(),
            snakeDirection = state.snake.direction,
            dispatcher = dispatcher
        )

        //Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
private fun GameViewDisplayPreview() {
    ClassicSnakeTheme {
        GameViewDisplay(
            state = GameViewState.Display(
                snake = Snake(listOf(Point(5,5), Point(4,5), Point(3,5)), Direction.NONE),
                currentSnakeLives = 1,
                maxSnakeLives = 4,
                bonusItems = listOf(BonusItem(Point(0, 3), BonusType.IncreaseScore)),
                blockItems = listOf(Point(9, 2)),
                boardSize = GameBoardSize.Small,
                time = 0L,
                sessionTime = "00:00",
                score = 0,
                gameStatus = GameStatus.Playing,
                gameLevelConfig = GameLevelConfig.Hard,
                gameRules = GameRules()
            )
        ) {}
    }
}