package com.example.jetpackcompose_aniplus.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AnimeTitle(
    val type: String,
    val title: String
)
 {
}