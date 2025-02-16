package com.example.anime_app_jetpackcompose.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime_app_jetpackcompose.models.repository.AnimeRepository
import com.example.jetpackcompose_aniplus.data.dto.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleAnimeViewModel @Inject constructor(private val repository: AnimeRepository): ViewModel() {
    private val _singleAnime = MutableLiveData<Anime>()
    val singleAnime : LiveData<Anime> get() = _singleAnime

 /* fun getSingleAnime(id:Int){
          viewModelScope.launch (Dispatchers.IO){
              val anime=repository.getSingleAnime(id)
              _singleAnime.postValue(anime)
          }
    }*/
}