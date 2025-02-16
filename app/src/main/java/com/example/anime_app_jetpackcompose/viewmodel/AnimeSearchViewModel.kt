package com.example.anime_app_jetpackcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.anime_app_jetpackcompose.models.repository.AnimeRepository
import com.example.jetpackcompose_aniplus.data.dto.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class AnimeSearchViewModel @Inject constructor(private val repository: AnimeRepository): ViewModel() {

    fun getTopAnime(q: String):Flow<PagingData<Anime>> = repository
        .getAnimeSearch(q).cachedIn(viewModelScope)


    val queryFlow = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchResults : Flow<PagingData<Anime>> = queryFlow
        .flatMapLatest {
                query ->
            repository.getAnimeSearch(q = query)
        }.cachedIn(viewModelScope)

    fun onSearchQueryChange(newQuery: String) {
        queryFlow.value = newQuery
    }
}