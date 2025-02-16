package com.example.jetpackcompose_aniplus.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class DateProp(
    val day: Int?,
    val month: Int?,
    val year: Int?
)