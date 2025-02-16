package com.example.anime_app_jetpackcompose.view.anime_page

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.anime_app_jetpackcompose.ui.theme.AppTheme
import com.example.anime_app_jetpackcompose.view.CategoryChip
import com.example.anime_app_jetpackcompose.view.search.SearchCharactersActivity
import com.example.anime_app_jetpackcompose.view.ViewPagerSlider
import com.example.anime_app_jetpackcompose.view.manga_page.MangaCard
import com.example.anime_app_jetpackcompose.view.manga_page.MangaDetailActivity
import com.example.anime_app_jetpackcompose.view.settings.SettingsActivity
import com.example.anime_app_jetpackcompose.view.shimmer_effect.AnimeListShimmer
import com.example.anime_app_jetpackcompose.viewmodel.TopAnimeViewModel
import com.example.anime_app_jetpackcompose.viewmodel.TopMangaViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MissingPermission")
@Composable
fun TopAnimePage(navController: NavController) {
    val context = LocalContext.current
    val vm: TopAnimeViewModel = hiltViewModel()
    val anime = vm.getTopAnime().collectAsLazyPagingItems()
    var selectedCategory by remember { mutableStateOf("Action") }
    val vmManga: TopMangaViewModel = hiltViewModel()
    val manga = vmManga.getTopManga().collectAsLazyPagingItems()

    val categories = listOf("Action", "Adventure", "Avant Garde", "Award Winning", "Comedy", "Drama","Fantasy","Girls Love","Horror",
        "Mystery","Romance","Sci-Fi","Slice of Life","Sports","Supernatural","Ecchi","Detective", "Historical","Isekai","Mecha","Military","Music","Mythology","Psychological","Racing")
    // İnternet bağlantısını kontrol et
    var isConnected by remember { mutableStateOf(checkInternetConnection(context)) }

    // Sayfayı yenilemek için kullanacağımız state
    var isRefreshing by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AnimeTopBar(
                onClickSearch = {
                    val intent = Intent(context, SearchCharactersActivity::class.java)
                    context.startActivity(intent)
                }
                , onClickSettings = {
                    val intent = Intent(context, SettingsActivity::class.java)
                    context.startActivity(intent)
                }
            )
        },
        backgroundColor = AppTheme.colorScheme.backgroundColor
    ) { paddingValues ->

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = @androidx.annotation.RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE) {
                isRefreshing = true
                isConnected = checkInternetConnection(context)
                vm.isRefreshing
                vmManga.isRefreshing
                isRefreshing = false
            }
        ) {
            if (!isConnected) {
                // İnternet bağlantısı yoksa uyarı mesajı göster
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "İnternet bağlantısı yok!", color = Color(0xFFFF3748), fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {
                        isConnected = checkInternetConnection(context)
                        if (isConnected) {
                            vm.refreshAnime()
                            vmManga.refreshManga()
                        }
                    }) {
                        Text(text = "Tekrar Dene")
                    }
                }
            } else {
                // Ana içerik
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Slider Bölümü
                    item { ViewPagerSlider() }
                    item{
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 12.dp, top = 12.dp)
                        ) {
                            items(categories.size) { index ->
                                val category = categories[index]
                                AnimeCategoryChip(
                                    category = category,
                                    isSelected = category == selectedCategory,
                                    onClick = {
                                        selectedCategory = category
                                    }
                                )
                            }
                        }
                    }

                    // "Top Anime" Başlığı
                    item {
                        Row(modifier = Modifier.padding(start = 16.dp)) {
                            Text(
                                text = "Top Anime",
                                fontSize = 16.sp,
                                color = AppTheme.colorScheme.textColor,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                    }

                    // Anime Listesi
                    item {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(anime.itemCount) { index ->
                                anime[index]?.let {
                                    AnimeCard(anime = it, onClick = {
                                    /* Detay Sayfasına Git */
                                        val intent = Intent(context, AnimeDetailActivity::class.java).apply {
                                            putExtra("name", it.title)
                                            putExtra("id", it.mal_id)
                                            putExtra("image_url", it.images.webp.image_url)
                                            putExtra("anime_type", it.type)
                                            putExtra("anime_source", it.source)
                                            putExtra("anime_episodes", it.episodes)
                                            putExtra("anime_score", it.score)
                                            putExtra("anime_rank", it.rank)
                                            putExtra("anime_sypnosis",it.synopsis)
                                        }
                                        context.startActivity(intent)

                                    })
                                }
                            }

                            anime.apply {
                                when{
                                    loadState.refresh is LoadState.Loading -> {
                                        item {
                                            AnimeListShimmer()
                                        }
                                    }

                                    loadState.refresh is LoadState.Error -> {
                                        item {
                                            ErrorFetchingAnime(error = "Something went wrong while fetching anime")
                                        }
                                    }
                                }
                            }

                        }

                    }

                    // "Top Manga" Başlığı
                    item {
                        Row(modifier = Modifier.padding(start = 16.dp, top = 16.dp)) {
                            Text(
                                text = "Top Manga",
                                fontSize = 16.sp,
                                color = AppTheme.colorScheme.textColor,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                    }

                    item {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 12.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(manga.itemCount) { index ->
                                manga[index]?.let { MangaCard(it, onClick = {
                                    val intent = Intent(context, MangaDetailActivity::class.java).apply {
                                        putExtra("name", it.title)
                                        putExtra("id", it.mal_id)
                                        putExtra("image_url", it.images.webp.image_url)
                                        putExtra("anime_type", it.type)
                                        putExtra("anime_score", it.score)
                                        putExtra("anime_rank", it.rank)
                                        putExtra("anime_sypnosis",it.synopsis)
                                    }
                                    context.startActivity(intent)
                                }) }
                            }

                            manga.apply {
                                when{
                                    loadState.refresh is LoadState.Loading -> {
                                        item {
                                            AnimeListShimmer()
                                        }
                                    }

                                    loadState.refresh is LoadState.Error -> {
                                        item {
                                            ErrorFetchingAnime(error = "Something went wrong while fetching anime")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// İnternet bağlantısını kontrol eden fonksiyon
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun checkInternetConnection(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
    return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
}