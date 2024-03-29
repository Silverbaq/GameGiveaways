// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import data.repositories.repositoryModule
import org.koin.core.context.startKoin
import ui.Screens
import ui.giveawaydetails.GiveawayDetailsScreen
import ui.giveawaydetails.giveawayDetailsModule
import ui.giveaways.GiveawaysScreen
import ui.giveaways.giveawayModule

@Composable
@Preview
fun App() {
    var screenState by mutableStateOf(Screens.MAIN)
    var giveawayDetailsIdState by mutableStateOf(0)

    MaterialTheme {
        Crossfade(targetState = screenState, animationSpec = tween(1000)) { screen ->
            when (screen) {
                Screens.MAIN -> {
                    GiveawaysScreen { itemId ->
                        giveawayDetailsIdState = itemId
                        screenState = Screens.DETAILS
                    }
                }

                else -> {
                    GiveawayDetailsScreen(giveawayDetailsIdState) {
                        screenState = Screens.MAIN
                    }
                }
            }
        }
    }
}

fun main() = application {
    startKoin {
        printLogger()
        modules(appModule, repositoryModule, giveawayModule, giveawayDetailsModule)
    }

    Window(title = "Game giveaways", onCloseRequest = ::exitApplication) {
        App()
    }
}
