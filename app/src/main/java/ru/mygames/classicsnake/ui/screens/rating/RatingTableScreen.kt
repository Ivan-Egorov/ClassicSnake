package ru.mygames.classicsnake.ui.screens.rating

import androidx.compose.runtime.Composable
import ru.mygames.classicsnake.ui.screens.rating.views.RatingTableViewDisplay
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme

@Composable
fun RatingTableScreen() {
    ClassicSnakeTheme {
        RatingTableViewDisplay()
    }
}
