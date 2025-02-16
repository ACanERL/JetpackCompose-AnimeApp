package com.example.anime_app_jetpackcompose.view.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anime_app_jetpackcompose.ui.theme.AppTheme
import com.example.anime_app_jetpackcompose.view.anime_page.AnimeDetailsAppBar
import com.example.anime_app_jetpackcompose.viewmodel.ThemeViewModel
import androidx.compose.runtime.getValue

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsPage( onFinishClicked: () -> Unit){
    val viewModel: ThemeViewModel = viewModel()
    val isDarkTheme by viewModel.isDarkTheme.collectAsState()
    Scaffold(
        backgroundColor = AppTheme.colorScheme.backgroundColor,
        contentColor = AppTheme.colorScheme.textColor,
        topBar = {
            AnimeDetailsAppBar(
                name = "Settings",
                onFinishClicked = onFinishClicked
            )
        }
    ) { paddingValues ->
            Row (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(AppTheme.colorScheme.backgroundColor)
            ) {
                   // Text(text = "Theme", style = AppTheme.typography.titleMedium)
                    ThemeSwitcher(
                        isDarkTheme = isDarkTheme,
                        onThemeChange = { viewModel.toggleTheme() }
                    )
            }
    }
}

@Composable
fun ThemeSwitcher(isDarkTheme: Boolean, onThemeChange: () -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp)
            .fillMaxSize(),
    ) {
        Text(
            text = if (isDarkTheme) "Dark Mode" else "Light Mode",
            style = AppTheme.typography.titleMedium,
            color = AppTheme.colorScheme.textColor
        )
        Switch(
            checked = isDarkTheme,
            onCheckedChange = { onThemeChange() }
        )
    }

}

@Composable
fun MyTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
    AppTheme(
        isDarkTheme = darkTheme,
        content = content
    )
}