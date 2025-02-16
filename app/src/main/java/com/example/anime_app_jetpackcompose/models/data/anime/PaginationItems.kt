package com.example.jetpackcompose_aniplus.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PaginationItems(
    val count: Int,
    val total: Int,
    val per_page: Int
)