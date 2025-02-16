package com.example.anime_app_jetpackcompose.models.data.manga

import kotlinx.serialization.Serializable


@Serializable
data class Pagination(
    val last_visible_page: Int,
    val has_next_page: Boolean,
    val current_page: Int,
    val items: Items
)
