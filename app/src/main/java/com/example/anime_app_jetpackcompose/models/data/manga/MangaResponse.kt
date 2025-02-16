package com.example.anime_app_jetpackcompose.models.data.manga

import kotlinx.serialization.Serializable

@Serializable
data class MangaResponse(
    val pagination: Pagination,
    val data: List<Manga>
)
