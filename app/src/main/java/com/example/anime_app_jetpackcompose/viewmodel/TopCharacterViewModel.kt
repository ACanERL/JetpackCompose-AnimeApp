package com.example.anime_app_jetpackcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.anime_app_jetpackcompose.models.data.character.CharacterData
import com.example.anime_app_jetpackcompose.models.repository.AnimeRepository
import com.example.jetpackcompose_aniplus.data.dto.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TopCharacterViewModel @Inject constructor(private val animeRepository: AnimeRepository): ViewModel() {
    fun getTopCharacters():Flow<PagingData<CharacterData>> = animeRepository
        .getTopCharacters().cachedIn(viewModelScope)
}