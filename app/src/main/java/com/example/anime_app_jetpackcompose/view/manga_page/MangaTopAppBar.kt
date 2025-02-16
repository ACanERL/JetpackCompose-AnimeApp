package com.example.anime_app_jetpackcompose.view.manga_page

import androidx.compose.foundation.background
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.anime_app_jetpackcompose.ui.theme.AppTheme

@Composable
fun MangaTopAppBar(name: String) {
    TopAppBar(
        title = {
            Text(
                text = name
            )
        },
        modifier = Modifier.background(AppTheme.colorScheme.backgroundColor),
        backgroundColor = AppTheme.colorScheme.backgroundColor,
        contentColor = AppTheme.colorScheme.textColor,
    )
}