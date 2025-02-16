package com.example.anime_app_jetpackcompose.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.anime_app_jetpackcompose.models.data.manga.Manga
import com.example.anime_app_jetpackcompose.models.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopMangaViewModel @Inject constructor(private val repository: AnimeRepository): ViewModel() {
    fun getTopManga(): Flow<PagingData<Manga>> = repository.getTopManga().cachedIn(viewModelScope)

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    fun refreshManga() {
        viewModelScope.launch {
            _isRefreshing.value = true
            delay(1000) // Opsiyonel: Kullanıcıya animasyon süresi tanımak için
            _isRefreshing.value = false
        }
    }


    private val _mangaList = MutableLiveData<List<Manga>>()
    val mangaList: LiveData<List<Manga>> get() = _mangaList

    fun setMangaList(list: List<Manga>) {
        _mangaList.value = list
    }


    fun getTopMangaByType(type: String): Flow<PagingData<Manga>> {
        return repository.getTopMangaByType(type).cachedIn(viewModelScope)
    }

}