package com.example.anime_app_jetpackcompose.models.data.manga

import kotlinx.serialization.Serializable

@Serializable
data class PublishedProp(
    val from: DateProp,
    val to: DateProp?
)