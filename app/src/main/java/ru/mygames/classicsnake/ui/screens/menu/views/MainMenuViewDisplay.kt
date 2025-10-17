package ru.mygames.classicsnake.ui.screens.menu.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import ru.mygames.classicsnake.R
import ru.mygames.classicsnake.ui.theme.components.JetCheckBox
import ru.mygames.classicsnake.ui.theme.components.JetSwitch
import ru.mygames.classicsnake.ui.theme.components.JetTextButton

import ru.mygames.classicsnake.ui.navigation.NavScreen

@Composable
fun MainMenuViewDisplay(
    onOpenPage: (NavScreen) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 32.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.weight(3f))

        Image(
            //imageVector = ImageVector.vectorResource(id = R.drawable.ic_snake),
            painter = painterResource(id = R.drawable.caterpillar),
            contentDescription = "",
            //modifier = Modifier.size(256.dp))
            modifier = Modifier.size(256.dp),
            contentScale = ContentScale.Crop)

        Spacer(modifier = Modifier.weight(1f))

        JetTextButton(
            text = "New game",
            modifier = Modifier.fillMaxWidth(),
            onClick = remember{
                {
                    onOpenPage.invoke(NavScreen.Game)
                }
            }
        )
//        {
//            onOpenPage.invoke(NavScreen.Game)
//        }

        JetTextButton(
            text = "Rating",
            modifier = Modifier.fillMaxWidth(),
            onClick = remember{
                {
                    onOpenPage.invoke(NavScreen.RatingTable)
                }
            }
        )
//        {
//            onOpenPage.invoke(NavScreen.RatingTable)
//        }

        JetTextButton(
            text = "Options",
            modifier = Modifier.fillMaxWidth(),
            onClick = remember{
                {
                    onOpenPage.invoke(NavScreen.Settings)
                }
            }
        )
//        {
//            onOpenPage.invoke(NavScreen.Settings)
//        }

    }
}

@Preview(showBackground = true)
@Composable
private fun MainMenuViewDisplayPreview() {
    ClassicSnakeTheme {
        MainMenuViewDisplay() {}
    }
}