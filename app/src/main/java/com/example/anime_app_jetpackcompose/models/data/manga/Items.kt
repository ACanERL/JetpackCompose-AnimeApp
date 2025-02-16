package com.example.anime_app_jetpackcompose.models.data.manga

import kotlinx.serialization.Serializable

@Serializable
data class Items(
    val count: Int,
    val total: Int,
    val per_page: Int
)