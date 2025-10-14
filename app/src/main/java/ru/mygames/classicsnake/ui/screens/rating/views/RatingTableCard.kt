package ru.mygames.classicsnake.ui.screens.rating.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mygames.classicsnake.domain.models.UserGameResult
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme

import com.microsoft.fluent.mobile.icons.R

@Composable
fun RatingTableCard(
    results: List<UserGameResult>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(64.dp, 64.dp, 32.dp, 32.dp)
            )
            .border(
                2.dp,
                color = MaterialTheme.colorScheme.primaryContainer.copy(0.25f),
                shape = RoundedCornerShape(64.dp, 64.dp, 32.dp, 32.dp)
            )
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(
                text = "Place",
                color = MaterialTheme.colorScheme.primary.copy(0.5f),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Score",
                color = MaterialTheme.colorScheme.primary.copy(0.5f),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(2f),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Time",
                color = MaterialTheme.colorScheme.primary.copy(0.5f),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }

        if (results.isNotEmpty()) {
            results.forEachIndexed { index, userGameResult ->
                RatingTableItemCard(
                    number = index + 1,
                    result = userGameResult
                )
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_fluent_calendar_empty_24_filled),
                        contentDescription = "",
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.tertiaryContainer,
                    )
                    Text(
                        text = "Rating table is empty",
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun RatingTableCardPreview() {
    ClassicSnakeTheme {
        RatingTableCard(
            results = emptyList()
        )
    }
}

@Preview
@Composable
private fun RatingTableCardPreview2() {
    ClassicSnakeTheme {
        RatingTableCard(
            results = listOf(
                UserGameResult(2350, "12:37"),
                UserGameResult(2220, "11:55"),
                UserGameResult(1980, "14:50"),
                UserGameResult(1880, "09:52")
            )
        )
    }
}