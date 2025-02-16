package com.example.jetpackcompose_aniplus.data.dto


import kotlinx.serialization.Serializable

@Serializable
data class AiredProp(
    val from: DateProp?,
    val to: DateProp?
)