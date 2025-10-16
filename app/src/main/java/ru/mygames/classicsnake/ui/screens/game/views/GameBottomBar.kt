package ru.mygames.classicsnake.ui.screens.game.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.microsoft.fluent.mobile.icons.R
import ru.mygames.classicsnake.domain.models.Direction
import ru.mygames.classicsnake.ui.screens.game.models.GameEvent

import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme
import ru.mygames.classicsnake.ui.theme.components.JetIconButton

@Composable
fun GameBottomBar(
    modifier: Modifier = Modifier,
    snakeDirection: Direction,
    dispatcher: (GameEvent) -> Unit
) {
    Box(modifier = modifier
        .background(
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(0.15f),
            //color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(16.dp, 16.dp, 64.dp, 64.dp))
    ) {
        JetIconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp)
                .size(72.dp, 48.dp),
            resId = R.drawable.ic_fluent_settings_24_filled,
            iconColor = Color.White) {
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 64.dp, top = 96.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            JetIconButton(
                modifier = Modifier.size(74.dp, 48.dp),
                resId = R.drawable.ic_fluent_arrow_up_24_filled,
                iconColor = Color.Black,
                enabled = snakeDirection != Direction.DOWN
            ) {
                dispatcher.invoke(GameEvent.ChangeSnakeDirection(Direction.UP))
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                JetIconButton(
                    modifier = Modifier.size(74.dp, 48.dp),
                    resId = R.drawable.ic_fluent_arrow_left_24_filled,
                    iconColor = Color.Black,
                    enabled = snakeDirection != Direction.RIGHT
                ) {
                    dispatcher.invoke(GameEvent.ChangeSnakeDirection(Direction.LEFT))
                }

                JetIconButton(
                    modifier = Modifier.size(74.dp, 48.dp),
                    resId = R.drawable.ic_fluent_arrow_down_24_filled,
                    iconColor = Color.Black,
                    enabled = snakeDirection != Direction.UP
                ) {
                    dispatcher.invoke(GameEvent.ChangeSnakeDirection(Direction.DOWN))
                }

                JetIconButton(
                    modifier = Modifier.size(74.dp, 48.dp),
                    resId = R.drawable.ic_fluent_arrow_right_24_filled,
                    iconColor = Color.Black,
                    enabled = snakeDirection != Direction.LEFT
                ) {
                    dispatcher.invoke(GameEvent.ChangeSnakeDirection(Direction.RIGHT))
                }
            }
        }
    }
}

@Preview
@Composable
private fun GameBottomBarPreview() {
    ClassicSnakeTheme {
        GameBottomBar(
            modifier = Modifier.fillMaxWidth(),
            snakeDirection = Direction.LEFT,
        ) {}
    }
}