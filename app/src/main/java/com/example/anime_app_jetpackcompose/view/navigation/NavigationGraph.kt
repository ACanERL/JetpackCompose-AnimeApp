package com.example.anime_app_jetpackcompose.view.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.anime_app_jetpackcompose.view.anime_page.TopAnimePage
import com.example.anime_app_jetpackcompose.view.character_page.TopCharacterPage
import com.example.anime_app_jetpackcompose.view.manga_page.TopMangaPage

@Composable
fun NavigationGraph(navController: NavHostController) {
    Surface {
        NavHost(
            navController = navController,
            startDestination = NavItems.Anime.routeName,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None},
            popExitTransition = { ExitTransition.None}
        ) {

            composable(NavItems.Anime.routeName) {
                TopAnimePage(navController = navController)
            }

            composable(NavItems.Character.routeName) {
                TopCharacterPage(navController = navController)
            }

            composable(NavItems.Manga.routeName) {
                TopMangaPage(navController = navController)
            }
        }
    }
}