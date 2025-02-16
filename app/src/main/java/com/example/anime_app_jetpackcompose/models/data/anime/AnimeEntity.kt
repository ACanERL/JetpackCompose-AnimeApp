package com.example.jetpackcompose_aniplus.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AnimeEntity(
    val mal_id: Int?,
    val type: String?,
    val name: String?,
    val url: String?
)