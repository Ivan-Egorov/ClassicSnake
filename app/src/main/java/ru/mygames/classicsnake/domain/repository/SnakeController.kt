package ru.mygames.classicsnake.domain.repository

import ru.mygames.classicsnake.data.local.datastore.GameBoardSize
import ru.mygames.classicsnake.domain.models.BonusItem
import ru.mygames.classicsnake.domain.models.CollisionStatus
import ru.mygames.classicsnake.domain.models.Direction
import ru.mygames.classicsnake.domain.models.Point
import ru.mygames.classicsnake.domain.models.Snake

class SnakeController {

    fun spawn(point: Point): Snake {
        return Snake(body = listOf(point), direction = Direction.NONE)
    }

    fun rotate(snake: Snake, direction: Direction): Snake {
        return snake.copy(direction = direction)
    }

    fun move(snake: Snake): Snake {
        if (snake.body.isEmpty()) return snake

        if (snake.direction == Direction.NONE) return snake

        val body = snake.body.toMutableList()
        body.add(0, Point(body[0].x + snake.direction.dx, body[0].y + snake.direction.dy))

        if (body.size > 1) {
            body.removeAt(body.lastIndex)
        }

        return snake.copy(body = body)
    }

    fun checkCollision(
        snake: Snake,
        boardSize: GameBoardSize,
        bonusItems: List<BonusItem>,
        blockItems: List<Point>
    ): CollisionStatus {
        val snakeCollision = checkSnakeCollision(snake)
        val boardCollision = checkBoardCollision(snake, boardSize)
        val bonusItemCollision = checkBonusItemCollision(snake, bonusItems)
        val blockItemCollision = checkBlockItemCollision(snake, blockItems)

        return if (snakeCollision != CollisionStatus.None) {
            snakeCollision
        } else if (boardCollision != CollisionStatus.None) {
            boardCollision
        } else if (blockItemCollision != CollisionStatus.None) {
            blockItemCollision
        } else if (bonusItemCollision != CollisionStatus.None) {
            bonusItemCollision
        } else {
            CollisionStatus.None
        }
    }

    private fun checkSnakeCollision(snake: Snake): CollisionStatus {
        if (snake.body.size > 4) {
            for (index in 1..snake.body.lastIndex) {
                if (
                    snake.body[index].x == snake.body.first().x &&
                    snake.body[index].y == snake.body.first().y
                ) {
                    return CollisionStatus.Snake(snake.body.first())
                }
            }
        }

        return CollisionStatus.None
    }

    private fun checkBoardCollision(
        snake: Snake,
        boardSize: GameBoardSize
    ): CollisionStatus {
        if (snake.body.isNotEmpty()) {
            val newX = snake.body.first().x + snake.direction.dx
            val newY = snake.body.first().y + snake.direction.dy

            if (
                newX < 0 ||
                newY < 0 ||
                newX >= boardSize.columns ||
                newY >= boardSize.rows
            ) {
                return CollisionStatus.Board(snake.body.first())
            }
        }

        return CollisionStatus.None
    }

    private fun checkBonusItemCollision(
        snake: Snake,
        bonusItems: List<BonusItem>
    ): CollisionStatus {
        if (snake.body.isNotEmpty()) {
            bonusItems.forEach {
                if (
                    it.position.x == snake.body.first().x &&
                    it.position.y == snake.body.first().y
                ) {
                    return CollisionStatus.BonusItem(snake.body.first())
                }
            }
        }

        return CollisionStatus.None
    }

    private fun checkBlockItemCollision(
        snake: Snake,
        blockItems: List<Point>
    ): CollisionStatus {
        if (snake.body.isNotEmpty()) {
            val newX = snake.body.first().x + snake.direction.dx
            val newY = snake.body.first().y + snake.direction.dy

            blockItems.forEach { item ->
                if (item.x == newX && item.y == newY) {
                    return CollisionStatus.BlockItem(snake.body.first())
                }
            }
        }

        return CollisionStatus.None

    }

    fun grow(snake: Snake): Snake {
        if (snake.body.isEmpty()) return snake

        val body = snake.body.toMutableList()
        body.add(Point(body.last().x, body.last().y))

        return snake.copy(body = body)
    }

    fun discard(snake: Snake, distance: Int): Snake {
        val body = snake.body.map { item ->
            item.copy(
                item.x - snake.direction.dx * distance,
                item.y - snake.direction.dy * distance
            )
        }

        return snake.copy(body = body)
    }

    fun throwTail(snake: Snake, point: Point): Snake {
        val index = snake.body.indexOfLast { it == point }

        if (index == -1) return snake

        val body = snake.body.dropLast(snake.body.lastIndex - index + 1)

        return snake.copy(body = body)
    }
}
