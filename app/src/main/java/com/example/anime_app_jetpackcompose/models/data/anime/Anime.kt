package com.example.jetpackcompose_aniplus.data.dto


import kotlinx.serialization.Serializable

@Serializable
data class Anime(
    val mal_id: Int,
    val url: String,
    val images: AnimeImages,
    val trailer: AnimeTrailer?,
    val approved: Boolean,
    val titles: List<AnimeTitle>,
    val title: String,
    val title_english: String?,
    val title_japanese: String?,
    val title_synonyms: List<String>?,
    val type: String,
    val source: String,
    val episodes: Int?,
    val status: String,
    val airing: Boolean,
    val aired: AnimeAired,
    val duration: String?,
    val rating: String?,
    val score: Double?,
    val scored_by: Int?,
    val rank: Int?,
    val popularity: Int?,
    val members: Int?,
    val favorites: Int?,
    val synopsis: String?,
    val background: String?,
    val season: String?,
    val year: Int?,
    val broadcast: AnimeBroadcast?,
    val producers: List<AnimeEntity>?,
    val licensors: List<AnimeEntity>?,
    val studios: List<AnimeEntity>?,
    val genres: List<AnimeEntity>?,
    val explicit_genres: List<AnimeEntity>?,
    val themes: List<AnimeEntity>?,
    val demographics: List<AnimeEntity>?
)