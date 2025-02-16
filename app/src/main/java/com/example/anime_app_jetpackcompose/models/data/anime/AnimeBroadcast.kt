package com.example.jetpackcompose_aniplus.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AnimeBroadcast(
    val day: String?,
    val time: String?,
    val timezone: String?,
    val string: String?
)