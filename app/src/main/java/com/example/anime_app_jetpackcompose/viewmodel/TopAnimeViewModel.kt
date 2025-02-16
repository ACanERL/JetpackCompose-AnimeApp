package com.example.anime_app_jetpackcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.anime_app_jetpackcompose.models.repository.AnimeRepository
import com.example.jetpackcompose_aniplus.data.dto.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopAnimeViewModel @Inject constructor(private val animeRepository: AnimeRepository): ViewModel(){
    fun getTopAnime():Flow<PagingData<Anime>> = animeRepository
        .getTopAnime().cachedIn(viewModelScope)

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    fun refreshAnime() {
        viewModelScope.launch {
            _isRefreshing.value = true
            delay(1000) // Opsiyonel: Kullanıcıya animasyon süresi tanımak için
            _isRefreshing.value = false
        }
    }
}