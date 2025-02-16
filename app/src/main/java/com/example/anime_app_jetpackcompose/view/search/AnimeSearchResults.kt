package com.example.anime_app_jetpackcompose.view.search

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.anime_app_jetpackcompose.ui.theme.AppTheme
import com.example.anime_app_jetpackcompose.view.anime_page.AnimeDetailActivity
import com.example.jetpackcompose_aniplus.data.dto.Anime


@Composable
fun CharacterSearchResults(context: Context, searchResults: LazyPagingItems<Anime>) {
    LazyColumn(
        modifier = Modifier.padding(all = 10.dp)
    ) {
        items(count = searchResults.itemCount) {
                index ->
            searchResults[index]?.let {
                    anim ->
                Box(
                    modifier = Modifier.clickable(
                    onClick = {
                        val intent = Intent(context, AnimeDetailActivity::class.java).apply {
                            putExtra("name", anim.title)
                            putExtra("id", anim.mal_id)
                            putExtra("image_url", anim.images.webp.image_url)
                            putExtra("anime_type", anim.type)
                            putExtra("anime_source", anim.source)
                            putExtra("anime_episodes", anim.episodes)
                            putExtra("anime_score", anim.score)
                            putExtra("anime_rank", anim.rank)
                            putExtra("anime_sypnosis",anim.synopsis)
                        }
                        context.startActivity(intent)
                    }
                ))
                {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(AppTheme.colorScheme.backgroundColor), verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(anim.images.webp.image_url)
                                .crossfade(enable = true)
                                .build(),
                            contentDescription = "avatar",
                            modifier = Modifier
                                .height(120.dp)
                                .width(120.dp)
                        )
                        Text(
                            text = anim.title,
                            fontSize = 22.sp,
                            color = AppTheme.colorScheme.textColor,
                            fontWeight = FontWeight.Bold,
                            maxLines = 2
                        )
                    }
                }
            }
        }
    }
}