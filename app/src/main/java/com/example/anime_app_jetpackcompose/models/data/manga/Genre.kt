package com.example.anime_app_jetpackcompose.models.data.manga

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)