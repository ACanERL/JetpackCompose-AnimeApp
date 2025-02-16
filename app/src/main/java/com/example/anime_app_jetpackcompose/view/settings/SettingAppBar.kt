package com.example.anime_app_jetpackcompose.view.settings

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.anime_app_jetpackcompose.R
import com.example.anime_app_jetpackcompose.ui.theme.AppTheme

@Composable
fun SettingAppBar(name: String,onFinishClicked : () -> Unit){
    TopAppBar(
        title = {
            Text(text =name)
        },

        modifier = Modifier.background(AppTheme.colorScheme.backgroundColor),
        backgroundColor = AppTheme.colorScheme.backgroundColor,
        contentColor = AppTheme.colorScheme.textColor,
        navigationIcon = {
            IconButton(
                onClick = onFinishClicked
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_backarrow),
                    contentDescription = "back",
                    tint = AppTheme.colorScheme.textColor
                )
            }
        }
    )
}