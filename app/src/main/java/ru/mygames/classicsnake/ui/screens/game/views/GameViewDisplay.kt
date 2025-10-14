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
import ru.mygames.classicsnake.domain.models.Point
import ru.mygames.classicsnake.domain.models.Snake
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme

@Composable
fun GameViewDisplay() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        //Spacer(modifier = Modifier.weight(1f))

        GameTopBar(
            sessionTime = "08.52",
            score = 150,
            currentSnakeLives = 3,
            maxSnakeLives = 4,
            modifier = Modifier.fillMaxWidth()
        )

        //Spacer(modifier = Modifier.height(16.dp))

        GameBoard(
            snake = Snake(listOf(Point(5,5), Point(4,5), Point(3,5))),
            bonusItems = listOf(Point(0, 3)),
            blockItems = listOf(Point(9, 2))
        )

        GameBottomBar(
            modifier = Modifier.fillMaxWidth()
        )

        //Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
private fun GameViewDisplayPreview() {
    ClassicSnakeTheme {
        GameViewDisplay()
    }
}