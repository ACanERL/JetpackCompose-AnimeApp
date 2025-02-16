package com.example.anime_app_jetpackcompose.view.search

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anime_app_jetpackcompose.ui.theme.AppTheme
import com.example.anime_app_jetpackcompose.viewmodel.ThemeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCharactersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: ThemeViewModel = viewModel()
            val isDarkTheme = viewModel.isDarkTheme.collectAsState(initial = false).value
            val systemUiController = rememberSystemUiController()
            val darkTheme = MaterialTheme.colorScheme.background // You can use darkTheme for if-else statement
            val useDarkIcons = darkTheme == MaterialTheme.colorScheme.background // Decide if you want dark icons based on color
            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = darkTheme, // Set to a color from your theme
                    darkIcons = useDarkIcons // Set darkIcons based on the background
                )
                systemUiController.setStatusBarColor(
                    color = darkTheme, // Set to a color from your theme
                    darkIcons = useDarkIcons
                )
            }

            val context : Context = LocalContext.current


            AppTheme(isDarkTheme=isDarkTheme) {
                AnimeSearch(
                    onFinishClicked = {
                        finish()
                    },
                    context = context
                )
            }

        }
    }
}