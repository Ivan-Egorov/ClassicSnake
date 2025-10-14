package ru.mygames.classicsnake.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme

@Composable
fun JetTextButton(
    text: String,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(32.dp),
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
        .background(MaterialTheme.colorScheme.primaryContainer, shape)
        .clip(shape)
        .then(if (enabled) Modifier.clickable(onClick = onClick) else Modifier)
        .padding(PaddingValues(horizontal = 32.dp, vertical = 12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }

}

@Preview
@Composable
private fun JetTextButtonPreview() {
    ClassicSnakeTheme {
        JetTextButton(
            text = "New game",
            modifier = Modifier.fillMaxWidth()
        ) { }
    }
}