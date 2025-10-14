package ru.mygames.classicsnake.ui.screens.game

import androidx.compose.runtime.Composable
import ru.mygames.classicsnake.ui.screens.game.views.GameViewDisplay
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme

@Composable
fun GameScreen() {
    ClassicSnakeTheme {
        GameViewDisplay()
    }
}