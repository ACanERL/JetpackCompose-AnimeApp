package com.example.jetpackcompose_aniplus.data.dto


import kotlinx.serialization.Serializable

@Serializable
data class AnimeResponse(
    val pagination: Pagination,
    val data: List<Anime>
)