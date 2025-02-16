package com.example.anime_app_jetpackcompose.models.api

import com.example.anime_app_jetpackcompose.models.data.character.CharacterResponse
import com.example.anime_app_jetpackcompose.models.data.manga.MangaResponse
import com.example.jetpackcompose_aniplus.data.dto.AnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("anime")
    suspend fun getAnimeSearch(
        @Query("page") page: Int,
        @Query("q") q: String
    ): AnimeResponse

    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("page") page: Int
    ): AnimeResponse

    @GET("top/characters")
    suspend fun getTopCharacters(
        @Query("page") page: Int
    ): CharacterResponse

   // @GET("character/{id}")
   // suspend fun getSingleAnime(@Path("id") animeId :Int) : Anime


    @GET("top/manga")
    suspend fun getTopManga(
        @Query("page") page: Int
    ): MangaResponse

    @GET("top/manga")
    suspend fun getTopMangaType(
        @Query("page") page: Int,
        @Query("type") type: String?
    ): MangaResponse


}