package com.example.anime_app_jetpackcompose.models.data.character

data class CharacterData(
    val mal_id: Int,
    val url: String,
    val images: Images,
    val name: String,
    val name_kanji: String,
    val nicknames: List<String>,
    val favorites: Int,
    val about: String
)