package com.example.anime_app_jetpackcompose.view.anime_page

import android.R.attr.data
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerDefaults.backgroundColor
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.anime_app_jetpackcompose.ui.theme.AppTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AnimeDetails(
    name: String,
    id: Int,
    image_url: String,
    anime_type: String,
    anime_source: String,
    anime_episodes: Int,
    anime_score: Double,
    anime_rank: Int,
    anime_sypnosis:String,
    onFinishClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            AnimeDetailsAppBar(
                name = name,
                onFinishClicked = onFinishClicked
            )
        }
        , modifier = Modifier.background(AppTheme.colorScheme.backgroundColor)
    ) { padding ->
        LazyColumn(
            modifier = Modifier.background(AppTheme.colorScheme.backgroundColor)
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            item{
                Column(modifier = Modifier.fillMaxSize().background(AppTheme.colorScheme.backgroundColor),
                    horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                ) {
                    androidx.compose.material.Card(
                        modifier = Modifier
                            .padding(top=64.dp)
                            .width(160.dp).height(230.dp),
                        shape = RoundedCornerShape(16.dp),
                        backgroundColor = Color.White,
                        elevation = 6.dp,
                        border = BorderStroke(1.dp, Color.LightGray)
                    ){
                        AsyncImage(
                            model =image_url,
                            contentDescription = "Header Background",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )

                    }
                    Text( modifier = Modifier.padding(top = 12.dp),
                        text = name,
                        fontSize = 22.sp,
                        color = AppTheme.colorScheme.textColor,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally).padding(all = 8.dp)){
                        AnimeDetailInfo(label = "Rank", value = anime_rank.toString())
                        AnimeDetailInfo(label = "Score", value = anime_score.toString())
                        AnimeDetailInfo(label = "Type", value = anime_type)
                    }
                    AnimeDetailInfo(label = "Source", value = anime_source)
                    AnimeDetailInfo(label = "Episodes", value = anime_episodes.toString())
                    AnimeDetailInfo(label = "Description", value = anime_sypnosis.toString())
                }
            }

        }
    }
}
@Composable
fun AnimeDetailInfo(label: String, value: String) {
    Text(
        text = "$label: $value",
        fontSize = 18.sp,
        color = AppTheme.colorScheme.textColor,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(8.dp)
    )
}


