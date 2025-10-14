package ru.mygames.classicsnake.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.mygames.classicsnake.ui.screens.game.GameScreen
import ru.mygames.classicsnake.ui.screens.menu.MainMenuScreen
import ru.mygames.classicsnake.ui.screens.rating.RatingTableScreen
import ru.mygames.classicsnake.ui.screens.settings.SettingsScreen

@ExperimentalFoundationApi
@Composable
fun NavHostScreen(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = NavScreen.MainMenu
    ) {
        composable<NavScreen.MainMenu> {
            MainMenuScreen(navHostController)
        }

        composable<NavScreen.RatingTable> {
            RatingTableScreen()
        }

        composable<NavScreen.Settings> {
            SettingsScreen(navHostController)
        }

        composable<NavScreen.Game> {
            GameScreen()
        }
    }
}