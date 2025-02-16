package com.example.anime_app_jetpackcompose.models.data.manga

import kotlinx.serialization.Serializable

@Serializable
data class DateProp(
    val day: Int?,
    val month: Int?,
    val year: Int?
)
