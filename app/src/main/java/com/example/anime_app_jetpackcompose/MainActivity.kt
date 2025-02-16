package com.example.anime_app_jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anime_app_jetpackcompose.ui.theme.AppTheme
import com.example.anime_app_jetpackcompose.view.scaffold.ScaffoldLayout
import com.example.anime_app_jetpackcompose.viewmodel.ThemeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        installSplashScreen()
        setContent {
            val viewModel: ThemeViewModel = viewModel()
            val isDarkTheme = viewModel.isDarkTheme.collectAsState(initial = false).value
            AppTheme(isDarkTheme=isDarkTheme){


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
                App()
             }

        }
    }
}

@Composable
fun App(){
    Surface(modifier = Modifier.fillMaxSize()) {
        ScaffoldLayout()
    }
}