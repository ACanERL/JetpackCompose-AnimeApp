package com.example.jetpackcompose_aniplus.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ImageVariant(
    val image_url: String,
    val small_image_url: String,
    val large_image_url: String
)
