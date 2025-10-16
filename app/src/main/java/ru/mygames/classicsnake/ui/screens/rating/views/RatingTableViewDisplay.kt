package ru.mygames.classicsnake.ui.screens.rating.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mygames.classicsnake.data.local.datastore.GameLevel
import ru.mygames.classicsnake.domain.models.UserGameResult
//import ru.mygames.classicsnake.data.local.datastore.UserGameResult
import ru.mygames.classicsnake.ui.screens.rating.models.RatingTableEvent
import ru.mygames.classicsnake.ui.screens.rating.models.RatingTableViewState
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme
import ru.mygames.classicsnake.ui.theme.components.JetSection
import ru.mygames.classicsnake.ui.theme.components.JetSwitch
import ru.mygames.classicsnake.ui.theme.components.JetTextButton

@Composable
fun RatingTableViewDisplay(
    state: RatingTableViewState.Display,
    dispatcher: (RatingTableEvent) -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Rating",
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
            ) { number: Int -> dispatcher.invoke(RatingTableEvent.ChangeDifficultyLevel(number)) }
        }

        JetSection(
            label = "Ranking",
            modifier = Modifier.weight(1f)
        ) {
            RatingTableCard(
                results = state.gameResults
                /*results = listOf(
                    UserGameResult(350, "02:37"),
                    UserGameResult(220, "01:55"),
                    UserGameResult(190, "01:50"),
                    UserGameResult(180, "00:52")
                )*/
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        JetTextButton(
            text = "Return",
            modifier = Modifier.fillMaxWidth()
        ) { dispatcher.invoke(RatingTableEvent.CloseScreen) }
    }
}

@Preview
@Composable
private fun RatingTableViewDisplayPreview() {
    ClassicSnakeTheme {
        RatingTableViewDisplay(
            RatingTableViewState.Display(
                GameLevel.Easy,
                listOf(
                    UserGameResult(350, "02:37"),
                    UserGameResult(220, "01:55"),
                    UserGameResult(190, "01:50"),
                    UserGameResult(180, "00:52")
                )
            )
        ) {}
    }
}