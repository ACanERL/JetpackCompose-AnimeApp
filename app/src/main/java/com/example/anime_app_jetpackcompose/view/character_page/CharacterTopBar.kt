package com.example.anime_app_jetpackcompose.view.character_page

import androidx.compose.foundation.background
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.anime_app_jetpackcompose.R
import com.example.anime_app_jetpackcompose.ui.theme.AppTheme

@Composable
fun CharacterTopBar(onClick:()-> Unit){
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.top_character))
        },
        actions = {
            IconButton(
                onClick = onClick
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(id = R.string.search_anime))
            }
        },
        modifier = Modifier.background(AppTheme.colorScheme.backgroundColor),
        backgroundColor = AppTheme.colorScheme.backgroundColor,
        contentColor = AppTheme.colorScheme.textColor
    )

}