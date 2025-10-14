package ru.mygames.classicsnake.ui.screens.rating.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mygames.classicsnake.domain.models.UserGameResult
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme

@Composable
fun RatingTableItemCard(
    number: Int,
    result: UserGameResult,
    modifier: Modifier = Modifier
) {
    val textColor = when (number) {
        1 -> MaterialTheme.colorScheme.primary
        2 -> MaterialTheme.colorScheme.primary.copy(0.85f)
        3 -> MaterialTheme.colorScheme.primary.copy(0.75f)
        else -> MaterialTheme.colorScheme.primary.copy(0.5f)
    }

    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(64.dp, 64.dp, 16.dp, 16.dp))
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = number.toString(),
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center)
        Text(
            text = result.score.toString(),
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.Center)
        Text(
            text = result.time,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center)
    }
}

@Preview
@Composable
private fun RatingTableItemCardPreview() {
    ClassicSnakeTheme {
        RatingTableItemCard(
            number = 2,
            result = UserGameResult(
                score = 2250,
                time = "22:30"
            )
        )
    }
}