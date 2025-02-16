package com.example.anime_app_jetpackcompose.models.data.manga

import kotlinx.serialization.Serializable

@Serializable
data class Title(
    val type: String,
    val title: String
)
