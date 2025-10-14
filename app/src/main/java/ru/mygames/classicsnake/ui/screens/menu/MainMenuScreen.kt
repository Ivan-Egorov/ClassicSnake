package ru.mygames.classicsnake.ui.screens.menu

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ru.mygames.classicsnake.ui.navigation.NavScreen
import ru.mygames.classicsnake.ui.screens.menu.views.MainMenuViewDisplay
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme

@Composable
fun MainMenuScreen(navController: NavController) {
    MainMenuViewDisplay(
        onOpenPage = { page: NavScreen -> navController.navigate(page) }
    )
}