package com.example.anime_app_jetpackcompose.view.manga_page

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.anime_app_jetpackcompose.ui.theme.AppTheme
import com.example.anime_app_jetpackcompose.view.CategoryChip
import com.example.anime_app_jetpackcompose.view.shimmer_effect.CharactersShimmer
import com.example.anime_app_jetpackcompose.viewmodel.TopMangaViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopMangaPage(navController: NavController) {
    val mangaViewModel: TopMangaViewModel = hiltViewModel()

    var selectedCategory by remember { mutableStateOf("Manga") } // Varsayılan kategori
    val mangaList = mangaViewModel.getTopMangaByType(selectedCategory.lowercase()).collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MangaTopAppBar(name = selectedCategory.toString())
        }
        , modifier = Modifier.background(AppTheme.colorScheme.backgroundColor)
    ) {
        Column(modifier = Modifier.fillMaxSize().background(AppTheme.colorScheme.backgroundColor)) {
            val categories = listOf("Manga", "Novel", "Lightnovel", "Oneshot", "Manhwa", "Manhua")
            // Kategori Seçimi
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 64.dp)
            ) {
                items(categories.size) { index ->
                    val category = categories[index]
                    CategoryChip(
                        category = category,
                        isSelected = category == selectedCategory,
                        onClick = {
                            selectedCategory = category
                        }
                    )
                }
            }
            // Manga Listesi
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(8.dp).background(AppTheme.colorScheme.backgroundColor),
              //  verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(mangaList.itemCount) { index ->
                    mangaList[index]?.let { manga ->
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(manga.images.webp.image_url)
                                .crossfade(enable = true)
                                .build(),
                            contentDescription = "anime image",
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp)) // Hafif yuvarlatılmış köşeler
                                .height(140.dp) // Daha büyük resim
                                .width(140.dp)
                        )
                        Text(
                            text = manga.title,
                            fontSize = 18.sp,
                            color = AppTheme.colorScheme.textColor,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(12.dp),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = manga.background.toString(),
                            fontSize = 16.sp,
                            color = AppTheme.colorScheme.textColor,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
                // Yükleme ve Hata Durumları
                mangaList.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    CharactersShimmer()
                                }
                            }
                        }

                        loadState.refresh is LoadState.Error -> {
                            item {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    CharactersShimmer()
                                }
                            }
                        }

                        loadState.append is LoadState.Loading -> {
                            item {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}