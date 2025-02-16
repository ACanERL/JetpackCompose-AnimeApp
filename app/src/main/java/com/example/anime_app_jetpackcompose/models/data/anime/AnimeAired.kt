package com.example.jetpackcompose_aniplus.data.dto


import kotlinx.serialization.Serializable

@Serializable
data class AnimeAired(
    val from: String?,
    val to: String?,
    val prop: AiredProp?,
    val string: String?
)