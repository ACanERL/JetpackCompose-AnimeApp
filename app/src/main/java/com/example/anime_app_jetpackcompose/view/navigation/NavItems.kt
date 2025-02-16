package com.example.anime_app_jetpackcompose.view.navigation

import com.example.anime_app_jetpackcompose.R

sealed class NavItems(val title: String, val icon: Int, val routeName: String) {
    object Anime : NavItems(title = "Anime", icon = R.drawable.anime_list, routeName = "anime")
    object Character: NavItems(title = "Character", icon = R.drawable.ic_character, routeName = "characters")
    object Manga: NavItems(title = "Manga", icon = R.drawable.ic_manga, routeName = "manga")
}