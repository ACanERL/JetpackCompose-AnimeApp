package com.example.anime_app_jetpackcompose


import com.example.anime_app_jetpackcompose.models.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {
    private val BASE_URL = "https://api.jikan.moe/v4/"
    @Provides
    @Singleton
    fun provideRetrofitInstance() : ApiService  = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}