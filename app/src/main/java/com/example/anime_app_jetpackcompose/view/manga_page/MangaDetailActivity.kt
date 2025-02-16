package com.example.anime_app_jetpackcompose.view.manga_page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anime_app_jetpackcompose.models.data.manga.Genre
import com.example.anime_app_jetpackcompose.ui.theme.AppTheme
import com.example.anime_app_jetpackcompose.view.anime_page.AnimeDetails
import com.example.anime_app_jetpackcompose.viewmodel.ThemeViewModel
import com.example.anime_app_jetpackcompose.viewmodel.TopMangaViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MangaDetailActivity :ComponentActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val name = intent.getStringExtra("name") ?: ""
        val id = intent.getIntExtra("id", 0)
        val img_url = intent.getStringExtra("image_url") ?: ""
        val anime_type = intent.getStringExtra("anime_type") ?: ""
        val anime_score = intent.getDoubleExtra("anime_score",0.0)
        val anime_rank = intent.getIntExtra("anime_rank", 0)
        val anime_sypnosis=intent.getStringExtra("anime_sypnosis")?:""

        setContent {
            val viewModel: ThemeViewModel = viewModel()
            val isDarkTheme = viewModel.isDarkTheme.collectAsState(initial = false).value
            AppTheme(isDarkTheme=isDarkTheme){
                val systemUiController = rememberSystemUiController()
                val darkTheme = MaterialTheme.colorScheme.background // You can use darkTheme for if-else statement
                val useDarkIcons = darkTheme == MaterialTheme.colorScheme.background // Decide if you want dark icons based on color
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = darkTheme, // Set to a color from your theme
                        darkIcons = useDarkIcons // Set darkIcons based on the background
                    )
                    systemUiController.setStatusBarColor(
                        color = darkTheme, // Set to a color from your theme
                        darkIcons = useDarkIcons
                    )
                }
                MangaDetails(
                    name = name,
                    id = id,
                    image_url = img_url,
                    anime_type = anime_type,
                    anime_score = anime_score,
                    anime_rank = anime_rank,
                    anime_sypnosis = anime_sypnosis,
                    onFinishClicked = { finish() }
                )
            }

        }
    }
}