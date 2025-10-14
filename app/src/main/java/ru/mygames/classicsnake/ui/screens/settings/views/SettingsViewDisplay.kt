package ru.mygames.classicsnake.ui.screens.settings.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import ru.mygames.classicsnake.data.local.datastore.GameBoardSize
import ru.mygames.classicsnake.data.local.datastore.GameLevel
import ru.mygames.classicsnake.data.local.datastore.GameRules
import ru.mygames.classicsnake.data.local.datastore.SpawnChanceOfBonusItems
import ru.mygames.classicsnake.ui.screens.settings.models.GameRuleEvent
import ru.mygames.classicsnake.ui.screens.settings.models.SettingsEvent
import ru.mygames.classicsnake.ui.screens.settings.models.SettingsViewState
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme
import ru.mygames.classicsnake.ui.theme.components.JetCheckBox
import ru.mygames.classicsnake.ui.theme.components.JetSection
import ru.mygames.classicsnake.ui.theme.components.JetSwitch
import ru.mygames.classicsnake.ui.theme.components.JetTextButton

@Composable
fun SettingsViewDisplay(
    state: SettingsViewState.Display,
    dispatcher: (SettingsEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
        Text(
            text = "Options",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge
        )

        JetSection(
            label = "Difficulty level"
        ) {
            JetSwitch(
                items = listOf("Easy", "Normal", "Difficult"),
                selectedItemId = state.gameLevel.ordinal,
                modifier = Modifier.fillMaxWidth()
            ) {
                newValue: Int -> dispatcher.invoke(SettingsEvent.ChangeDifficultyLevel(newValue))
            }
        }

        JetSection(
            label = "Bonus item spawn frequency"
        ) {
            JetSwitch(
                items = listOf("Rare", "Often", "Always"),
                selectedItemId = state.spawnChanceOfBonusItems.ordinal,
                modifier = Modifier.fillMaxWidth()
            ) {
                newValue: Int -> dispatcher.invoke(SettingsEvent.ChangeChance(newValue))
            }
        }

        JetSection(
            label = "Map size"
        ) {
            JetSwitch(
                items = listOf("Small", "Medium", "Large"),
                selectedItemId = state.boardSize.ordinal,
                modifier = Modifier.fillMaxWidth()
            ) {
                newValue: Int -> dispatcher.invoke(SettingsEvent.ChangeBoardSize(newValue))
            }
        }
//        Text(
//            text = "Rules of the game",
//            color = MaterialTheme.colorScheme.primary,
//            style = MaterialTheme.typography.labelSmall
//        )

        JetSection(
            label = "Rules of the game"
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(32.dp)
                    )
                    .background(
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        shape = RoundedCornerShape(32.dp)
                    )
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)

            ) {
                JetCheckBox(
                    checked = state.gameRules.damageColliderEnabled,
                    label = "Damage from walls"
                ) {
                    newValue: Boolean -> dispatcher.invoke(SettingsEvent.ChangeRules(GameRuleEvent.ChangeDamageCollider(newValue)))
                }

                JetCheckBox(
                    checked = state.gameRules.extraLivesEnabled,
                    label = "Extra lives"
                ) {
                    newValue: Boolean -> dispatcher.invoke(SettingsEvent.ChangeRules(GameRuleEvent.ChangeExtraLives(newValue)))
                }

                JetCheckBox(
                    checked = state.gameRules.throwTailEnabled,
                    label = "Tail shedding"
                ) {
                    newValue: Boolean -> dispatcher.invoke(SettingsEvent.ChangeRules(GameRuleEvent.ChangeThrowTail(newValue)))
                }

                JetCheckBox(
                    checked = state.gameRules.randomWallsEnabled,
                    label = "Random walls"
                ) {
                    newValue: Boolean -> dispatcher.invoke(SettingsEvent.ChangeRules(GameRuleEvent.ChangeRandomWalls(newValue)))
                }
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
            }
        }
        Spacer(
            modifier = Modifier.weight(1f)
        )
        JetTextButton(
            text = "Return",
            modifier = Modifier.fillMaxWidth()
        ) {
            dispatcher.invoke(SettingsEvent.CloseScreen)
        }
    }
}

@Preview
@Composable
private fun SettingsViewDisplayPreview() {
    ClassicSnakeTheme {
        SettingsViewDisplay(
            state = SettingsViewState.Display(
                GameLevel.Easy,
                GameBoardSize.Small,
                SpawnChanceOfBonusItems.Normal,
                GameRules()),
            dispatcher = {}
        )
    }
}