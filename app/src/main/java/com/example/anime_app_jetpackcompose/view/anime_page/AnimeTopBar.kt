package com.example.anime_app_jetpackcompose.view.anime_page

import androidx.compose.foundation.background
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.anime_app_jetpackcompose.R
import com.example.anime_app_jetpackcompose.ui.theme.AppTheme

@Composable
fun AnimeTopBar(onClickSearch:()-> Unit,onClickSettings:()-> Unit){
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.appname))
        },
        actions = {
            IconButton(
                onClick = onClickSearch
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(id = R.string.search_anime), tint = AppTheme.colorScheme.textColor)
            }
            IconButton(
                onClick = onClickSettings
            ) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = stringResource(id = R.string.search_anime))
            }
        },
        modifier = Modifier.background(AppTheme.colorScheme.backgroundColor),
        backgroundColor = AppTheme.colorScheme.backgroundColor,
        contentColor = AppTheme.colorScheme.textColor
    )
}