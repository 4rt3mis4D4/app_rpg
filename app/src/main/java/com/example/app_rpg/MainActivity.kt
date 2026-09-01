package com.example.app_rpg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.app_rpg.ui.theme.App_rpgTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App_rpgTheme {
                App()
            }
        }
    }
}

enum class Destination(
    val label: String,
    @param:DrawableRes val iconRes: Int
) {
    Dice("Dados", R.drawable.ic_dice),
    CharacterSheet("Ficha", R.drawable.ic_character_sheet),
    Playlist("Música", R.drawable.ic_playlist)
}

@Composable
fun App() {
    var current by rememberSaveable { mutableStateOf(Destination.Dice) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                Destination.entries.forEach { destination ->
                    NavigationBarItem(
                        selected = current == destination,
                        onClick = { current = destination },
                        icon = {
                            Icon(
                                painter = painterResource(destination.iconRes),
                                contentDescription = destination.label
                            )
                        },
                        label = { Text(destination.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        // innerPadding clears the bottom bar and the system bars, since
        // enableEdgeToEdge() lets content draw behind them.
        val contentModifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)

        when (current) {
            Destination.Dice -> DiceScreen(contentModifier)
            Destination.CharacterSheet -> FichaPersonagemScreen()
            Destination.Playlist -> PlaceholderScreen("Música", contentModifier)
        }
    }
}

@Composable
private fun PlaceholderScreen(
    title: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(title, style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AppPreview() {
    App_rpgTheme {
        App()
    }
}