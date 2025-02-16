package com.example.anime_app_jetpackcompose.view.settings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anime_app_jetpackcompose.ui.theme.AppTheme
import com.example.anime_app_jetpackcompose.viewmodel.ThemeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class SettingsActivity:ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val viewModel: ThemeViewModel = viewModel()
            val isDarkTheme = viewModel.isDarkTheme.collectAsState(initial = false).value
            AppTheme(isDarkTheme=isDarkTheme) {
                val systemUiController = rememberSystemUiController()
                val darkTheme = MaterialTheme.colorScheme.background
                val useDarkIcons = darkTheme == MaterialTheme.colorScheme.background
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = darkTheme,
                        darkIcons = useDarkIcons
                    )
                    systemUiController.setStatusBarColor(
                        color = darkTheme,
                        darkIcons = useDarkIcons
                    )
                }
                SettingsPage( onFinishClicked = { finish() })
            }
        }
    }
}