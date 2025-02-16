package com.example.anime_app_jetpackcompose.models.data.character
data class Pagination(
    val last_visible_page: Int,
    val has_next_page: Boolean,
    val items: PaginationItems
)
