package com.example.anime_app_jetpackcompose.models.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.anime_app_jetpackcompose.models.api.ApiService
import com.example.anime_app_jetpackcompose.models.data.manga.Manga
import com.example.anime_app_jetpackcompose.models.pagination.AnimeSearchPagingSource
import com.example.anime_app_jetpackcompose.models.pagination.CharacterPagingSource
import com.example.anime_app_jetpackcompose.models.pagination.MangaTypeSource
import com.example.anime_app_jetpackcompose.models.pagination.TopAnimePagingSource
import com.example.anime_app_jetpackcompose.models.pagination.TopMangaPagingSource
import com.example.jetpackcompose_aniplus.data.dto.Anime
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimeRepository @Inject constructor(private val apiService: ApiService) {
    fun getTopAnime() = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        pagingSourceFactory = {
            TopAnimePagingSource(apiService)
        }
    ).flow

    fun getTopCharacters()= Pager(
        config = PagingConfig(
            pageSize = 10
        ),
        pagingSourceFactory = {
            CharacterPagingSource(apiService)
        }
    ).flow


    fun getAnimeSearch(q: String): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10, // Sayfa başına kaç eleman yüklenecek
                enablePlaceholders = false
            ),
            pagingSourceFactory = { AnimeSearchPagingSource(apiService, q) }
        ).flow
    }


  /*  suspend fun getSingleAnime(id: Int): Anime{
        return apiService.getSingleAnime(id)
    }*/

    fun getTopManga()=Pager(
        config = PagingConfig(
            pageSize = 15
        ),
        pagingSourceFactory = {
            TopMangaPagingSource(apiService)
        }
    ).flow

    fun getTopMangaByType(type: String): Flow<PagingData<Manga>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10, // Sayfa başına kaç eleman yüklenecek
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MangaTypeSource(apiService, type) }
        ).flow
    }
}