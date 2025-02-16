package com.example.anime_app_jetpackcompose.models.data.manga

import kotlinx.serialization.Serializable

@Serializable
data class Images(
    val jpg: ImageFormats,
    val webp: ImageFormats
)
