package com.example.anime_app_jetpackcompose.view.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.anime_app_jetpackcompose.R
import com.example.anime_app_jetpackcompose.ui.theme.AppTheme

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf<NavItems>(
        NavItems.Anime,
        NavItems.Character,
        NavItems.Manga,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = AppTheme.colorScheme.backgroundColor,
        contentColor = contentColorFor(AppTheme.colorScheme.textColor)
    ) {

        items.forEach() {
                item ->
            BottomNavigationItem(
                selected = item.routeName == currentRoute,
                onClick = {

                    navController.navigate(item.routeName) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }

                },
                icon = {
                    Icon(painter = painterResource(id = item.icon), contentDescription = item.title)
                },
               /* label = {
                    Text(
                        text = item.title,
                        softWrap = false,
                    )
                },*/
                alwaysShowLabel = true,
                selectedContentColor = Color.White,
                unselectedContentColor = AppTheme.colorScheme.textColor,
            )
        }
    }

}