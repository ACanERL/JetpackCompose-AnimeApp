package com.example.anime_app_jetpackcompose.view.character_page

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.anime_app_jetpackcompose.R
import com.example.anime_app_jetpackcompose.ui.theme.AppTheme
import com.example.anime_app_jetpackcompose.view.search.SearchCharactersActivity
import com.example.anime_app_jetpackcompose.view.anime_page.AnimeCard
import com.example.anime_app_jetpackcompose.view.anime_page.AnimeTopBar
import com.example.anime_app_jetpackcompose.view.anime_page.ErrorFetchingAnime
import com.example.anime_app_jetpackcompose.view.shimmer_effect.AnimeListShimmer
import com.example.anime_app_jetpackcompose.view.shimmer_effect.CharactersShimmer
import com.example.anime_app_jetpackcompose.viewmodel.TopAnimeViewModel
import com.example.anime_app_jetpackcompose.viewmodel.TopCharacterViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TopCharacterPage(navController: NavController) {
    val context = LocalContext.current
    val vmCh: TopCharacterViewModel = hiltViewModel<TopCharacterViewModel>()
    val characters=vmCh.getTopCharacters().collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            CharacterTopBar(
                onClick = {
                    val intent = Intent(context, SearchCharactersActivity::class.java)
                    context.startActivity(intent)
                }
            )
        },
        backgroundColor = AppTheme.colorScheme.backgroundColor
    )
    {
        Column (modifier = Modifier.fillMaxSize())
        {
            LazyColumn {
                items(characters.itemCount){ index->
                    characters[index]?.let{
                        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                            Card(
                                modifier = Modifier
                                    .padding(all= 12.dp)
                                ,shape = RoundedCornerShape(16.dp),
                                backgroundColor = AppTheme.colorScheme.backgroundColor,
                                elevation = 6.dp,
                                border = BorderStroke(1.dp, Color.LightGray)
                            ){
                                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(it.images.jpg.image_url)
                                            .crossfade(enable = true)
                                            .build(),
                                        contentDescription = "avatar",
                                        modifier = Modifier.padding(12.dp)
                                            .height(120.dp)
                                            .width(120.dp)
                                    )
                                    Column(){
                                        Text( modifier = Modifier.padding(start = 0.dp),
                                            text = it.name,
                                            fontSize = 20.sp,
                                            color = AppTheme.colorScheme.textColor,
                                            fontWeight = FontWeight.Bold,
                                            maxLines = 2
                                        )
                                        Text( modifier = Modifier.padding(start = 0.dp),
                                            text = it.about,
                                            fontSize = 18.sp,
                                            color = AppTheme.colorScheme.textColor,
                                            fontWeight = FontWeight.Medium,
                                            maxLines = 2
                                        )
                                    }
                                }

                            }


                        }
                    }
                }
                characters.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item {
                                CharactersShimmer()
                            }
                        }

                        loadState.refresh is LoadState.Error -> {
                            item {
                                ErrorFetchingAnime(error = "Something went wrong while fetching character")
                            }
                        }

                        loadState.append is LoadState.Loading -> {
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                ) {
                                    CircularProgressIndicator(color = Color.White)
                                }
                            }
                        }

                        loadState.append is LoadState.Error -> {
//                        item {
//                            Column(
//                                modifier = Modifier
//                                    .fillMaxWidth(),
//                                horizontalAlignment = Alignment.CenterHorizontally,
//                                verticalArrangement = Arrangement.Center,
//                            ) {
//                                Icon(
//                                    painter = painterResource(id = R.drawable.ic_error),
//                                    contentDescription = "error",
//                                    tint = Color.Red
//                                )
//                            }
//                        }
                        }
                    }
                }
            }
        }
    }
}