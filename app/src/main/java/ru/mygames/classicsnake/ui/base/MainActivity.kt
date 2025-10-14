package ru.mygames.classicsnake.ui.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.compose.rememberNavController
import ru.mygames.classicsnake.ui.navigation.NavHostScreen
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            ClassicSnakeTheme {
                NavHostScreen(navController)
            }
        }
    }
}
