package com.example.anime_app_jetpackcompose.models.data.manga

import kotlinx.serialization.Serializable

@Serializable
data class Manga(
    val mal_id: Int,
    val url: String,
    val images: Images,
    val approved: Boolean,
    val titles: List<Title>,
    val title: String,
    val title_english: String?,
    val title_japanese: String?,
    val title_synonyms: List<String>,
    val type: String,
    val chapters: Int?,
    val volumes: Int?,
    val status: String,
    val publishing: Boolean,
    val published: Published,
    val score: Double,
    val scored: Double,
    val scored_by: Int,
    val rank: Int,
    val popularity: Int,
    val members: Int,
    val favorites: Int,
    val synopsis: String?,
    val background: String?,
    val authors: List<Author>,
    val serializations: List<Serialization>,
    val genres: List<Genre>,
    val explicit_genres: List<Genre>,
    val themes: List<Genre>,
    val demographics: List<Genre>
)