package com.example.anime_app_jetpackcompose.view.manga_page

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.anime_app_jetpackcompose.models.data.manga.Manga
import com.example.anime_app_jetpackcompose.ui.theme.AppTheme

@Composable
fun MangaCard(manga: Manga?,onClick:()-> Unit){
    Card(
        modifier = Modifier.clickable(onClick = onClick)
            .padding(start = 12.dp)
            .width(160.dp).height(230.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = AppTheme.colorScheme.backgroundColor,
        elevation = 6.dp,
        border = BorderStroke(1.dp, Color.LightGray)
    ){
        Column(
            modifier = Modifier.padding(12.dp).background(AppTheme.colorScheme.backgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(manga?.images?.jpg?.image_url)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = "anime image",
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .width(100.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Anime Başlığı
            Text(
                text = manga?.title.toString(),
                fontSize = 14.sp,
                color = AppTheme.colorScheme.textColor,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(horizontal = 8.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = com.example.anime_app_jetpackcompose.R.drawable.ic_star_rate),
                    contentDescription = "Score",
                    tint = Color(0xFFFFD700),
                    modifier = Modifier.padding(end = 6.dp)
                        .width(18.dp)
                        .height(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = manga?.score.toString(),
                    fontSize = 14.sp,
                    color = AppTheme.colorScheme.textColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 4.dp)
                )
            }

        }
    }
}