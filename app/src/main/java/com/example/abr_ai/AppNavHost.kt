package com.example.abr_ai

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.abr_ai.ui.pages.ContentPage
import com.example.abr_ai.ui.pages.HomePage

object Home
object Content

@ExperimentalMaterial3Api
@Composable
fun AppNav() {
    val backStack = remember { mutableStateListOf<Any>(Home) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is Home -> NavEntry(key) {
                    HomePage(toContent = {
                        backStack.add(Content)
                    })
                }

                is Content -> NavEntry(key) {
                    ContentPage(toBack = {
                        backStack.removeLastOrNull()
                    })
                }

                else -> NavEntry(Unit) { Text("Unknown route") }
            }
        },
        transitionSpec = {
            slideInHorizontally(initialOffsetX = { it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { -it })
        },
        popTransitionSpec = {
            slideInHorizontally(initialOffsetX = {-it}) togetherWith
                    slideOutHorizontally(targetOffsetX = {it})
        },
        predictivePopTransitionSpec = {
            slideInHorizontally(initialOffsetX = {-it}) togetherWith
                    slideOutHorizontally(targetOffsetX = {it})
        }
    )
}
