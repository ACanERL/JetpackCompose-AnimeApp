package com.example.anime_app_jetpackcompose.view.anime_page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anime_app_jetpackcompose.ui.theme.AppTheme
import com.example.anime_app_jetpackcompose.viewmodel.ThemeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeDetailActivity :ComponentActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("name") ?: ""
        val id = intent.getIntExtra("id", 0)
        val img_url = intent.getStringExtra("image_url") ?: ""
        val anime_type = intent.getStringExtra("anime_type") ?: ""
        val anime_source = intent.getStringExtra("anime_source") ?: ""
        val anime_episodes = intent.getIntExtra("anime_episodes", 0)
        val anime_score = intent.getDoubleExtra("anime_score",0.0)
        val anime_rank = intent.getIntExtra("anime_rank", 0)
        val anime_sypnosis=intent.getStringExtra("anime_sypnosis")?:""

        setContent {
            val viewModel: ThemeViewModel = viewModel()
            val isDarkTheme = viewModel.isDarkTheme.collectAsState(initial = false).value
            AppTheme(isDarkTheme=isDarkTheme){
                val systemUiController = rememberSystemUiController()
                val darkTheme = MaterialTheme.colorScheme.background
                val useDarkIcons = darkTheme == MaterialTheme.colorScheme.background
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = darkTheme,
                        darkIcons = useDarkIcons
                    )
                    systemUiController.setStatusBarColor(
                        color = darkTheme,
                        darkIcons = useDarkIcons
                    )
                }
                AnimeDetails(
                    name = name,
                    id = id,
                    image_url = img_url,
                    anime_type = anime_type,
                    anime_source = anime_source,
                    anime_episodes = anime_episodes,
                    anime_score = anime_score,
                    anime_rank = anime_rank,
                    anime_sypnosis=anime_sypnosis,
                    onFinishClicked = { finish() }
                )
            }
        }
    }
}