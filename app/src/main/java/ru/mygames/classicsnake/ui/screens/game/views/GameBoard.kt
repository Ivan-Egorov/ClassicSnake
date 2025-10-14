package ru.mygames.classicsnake.ui.screens.game.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.scale
import ru.mygames.classicsnake.R
import ru.mygames.classicsnake.domain.models.Point
import ru.mygames.classicsnake.domain.models.Snake
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme



@Composable
fun GameBoard(
    modifier: Modifier = Modifier,
    snake: Snake,
    bonusItems: List<Point>,
    blockItems: List<Point>,
    //boardSize: GameBoardSize
) {
    val context = LocalContext.current

    val snakeHeadImage = remember {
        context.getDrawable(R.drawable.ic_snake_head)?.toBitmap()?.asImageBitmap()
    }

    val snakeTailImage = remember {
        context.getDrawable(R.drawable.ic_snake_tail)?.toBitmap()?.asImageBitmap()
    }

    val appleImage = remember {
        context.getDrawable(R.drawable.ic_apple)?.toBitmap()?.asImageBitmap()
    }

    val wallImage = remember {
        context.getDrawable(R.drawable.ic_wall)?.toBitmap()?.asImageBitmap()
    }

    BoxWithConstraints(
        modifier = modifier.border(
            width = 6.dp,
            color = MaterialTheme.colorScheme.onTertiaryContainer.copy(0.35f),
            //color = Color(0xFFD7E0D7),
            shape = RoundedCornerShape(16.dp)
        )
    ) {
        Canvas(
            modifier = Modifier
                .padding(6.dp)
                .requiredSize(this.maxWidth)
                //.requiredSize(this.maxWidth - 6.dp, this.maxWidth - 6.dp)
                .background(
                    color = MaterialTheme.colorScheme.onTertiaryContainer.copy(0.05f),
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            val fieldWidth = canvasWidth / 10
            val fieldHeight = canvasHeight / 10

            if(appleImage != null) {
                bonusItems.forEach { item ->
                    drawObject(
                        drawScope = this,
                        imageBitmap = appleImage,
                        coordinates = item,
                        width = fieldWidth,
                        height = fieldHeight
                    )
                }
            }

            if(wallImage != null) {
                blockItems.forEach { item ->
                    drawObject(
                        drawScope = this,
                        imageBitmap = wallImage,
                        coordinates = item,
                        width = fieldWidth,
                        height = fieldHeight
                    )
                }
            }

            if(snakeHeadImage != null && snakeTailImage != null) {
                drawSnake(
                    drawScope = this,
                    snake = snake,
                    snakeHead = snakeHeadImage,
                    snakeTail = snakeTailImage,
                    width = fieldWidth,
                    height = fieldHeight
                )
            }

        }
    }
}

fun drawObject(
    drawScope: DrawScope, // на чём рисуем
    imageBitmap: ImageBitmap, // что рисуем
    coordinates: Point, // где рисуем
    width: Float, // какой ширины
    height: Float // и какой высоты рисуем объект
) {
    val scaledBitmap: ImageBitmap = imageBitmap
        .asAndroidBitmap()
        .scale(
            width = width.toInt(),
            height = height.toInt()
        )
        .asImageBitmap()

    drawScope.drawImage(
        image = scaledBitmap,
        topLeft = Offset(
            coordinates.x * width,
            coordinates.y * height
        )
    )
}

fun drawSnake(
    drawScope: DrawScope, // на чём рисуем
    snake: Snake, // координаты змейки
    snakeHead: ImageBitmap, // голова змейки
    snakeTail: ImageBitmap, // хвост змейки
    width: Float, // ширина
    height: Float // высота элементов змейки
) {
    snake.body.forEachIndexed { index, point ->
        val image = if(index == 0) {
            snakeHead
        } else {
            snakeTail
        }

        drawObject(
            drawScope = drawScope,
            imageBitmap = image,
            coordinates = point,
            width = width,
            height = height
        )
    }
}

@Preview
@Composable
private fun GameBoardPreview() {
    ClassicSnakeTheme {
        GameBoard(
            snake = Snake(listOf(Point(5,5), Point(4,5), Point(3,5))),
            bonusItems = listOf(Point(0, 3)),
            blockItems = listOf(Point(9, 2))
        )
    }
}