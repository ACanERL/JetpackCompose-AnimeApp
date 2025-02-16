package com.example.jetpackcompose_aniplus.data.dto


import kotlinx.serialization.Serializable

@Serializable
data class AnimeImages(
    val jpg: ImageVariant,
    val webp: ImageVariant
)
