package com.example.anime_app_jetpackcompose.models.data.manga

import kotlinx.serialization.Serializable

@Serializable
data class Published(
    val from: String?,
    val to: String?,
    val prop: PublishedProp,
    val string: String
)
