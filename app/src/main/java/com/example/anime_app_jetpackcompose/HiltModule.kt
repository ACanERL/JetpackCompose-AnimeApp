package com.example.anime_app_jetpackcompose

import com.example.anime_app_jetpackcompose.models.api.ApiService
import com.example.anime_app_jetpackcompose.models.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun privateTopAnimeRepository(apiService: ApiService) = AnimeRepository(apiService)

}