package com.example.anime_app_jetpackcompose.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime_app_jetpackcompose.view.settings.ThemeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ThemeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ThemeRepository(application)

    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> get() = _isDarkTheme

    init {
        viewModelScope.launch {
            repository.isDarkTheme.collect {
                _isDarkTheme.value = it
            }
        }
    }

    fun toggleTheme() {
        viewModelScope.launch {
            repository.setDarkTheme(!_isDarkTheme.value)
        }
    }
}