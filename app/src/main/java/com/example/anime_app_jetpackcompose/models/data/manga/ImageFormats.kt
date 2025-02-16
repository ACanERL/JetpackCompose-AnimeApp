package com.example.anime_app_jetpackcompose.models.data.manga

import kotlinx.serialization.Serializable

@Serializable
data class ImageFormats(
    val image_url: String,
    val small_image_url: String,
    val large_image_url: String
)
