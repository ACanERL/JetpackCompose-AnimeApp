package com.example.anime_app_jetpackcompose.models.data.character

data class CharacterResponse(
    val data: List<CharacterData>,
    val pagination: Pagination
)