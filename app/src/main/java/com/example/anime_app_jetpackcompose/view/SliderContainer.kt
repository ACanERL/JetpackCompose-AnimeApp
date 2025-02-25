package com.example.anime_app_jetpackcompose.view

import android.graphics.PorterDuff
import android.widget.RatingBar
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.anime_app_jetpackcompose.models.data.imagefake.ImageData
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue
import com.example.anime_app_jetpackcompose.R
import com.example.anime_app_jetpackcompose.ui.theme.AppTheme

@ExperimentalPagerApi
@Composable
fun ViewPagerSlider(){
    val imageList=listOf(
        ImageData(
            "One Puch Man",
           9.9,
            "One Puch Man",
            R.drawable.onepuch
        ),
        ImageData(
            "One Puch Man 2",
            9.9,
            "One Puch Man",
            R.drawable.onepuch
        ),
        ImageData(
            "Solo Leveling",
            9.0,
            "ARise",
            R.drawable.solo
        )


    )
    val pagerState  = rememberPagerState(
        pageCount = imageList.size,
        initialPage =  2
    )



    LaunchedEffect(Unit){
        while (true){
            yield()
            delay(4000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    Column(modifier = Modifier.background(AppTheme.colorScheme.backgroundColor)) {

        HorizontalPager(state = pagerState,
            modifier = Modifier
        ) { page ->
            Card(modifier = Modifier

                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale

                    }
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )

                }
                .fillMaxWidth().padding(12.dp),
                shape = RoundedCornerShape(16.dp), // Yuvarlatılmış köşeler
                backgroundColor = Color.White, // Arka plan rengi
                elevation = 6.dp, // Hafif gölge efekti
                border = BorderStroke(1.dp, Color.LightGray)
            ) {

                val newKids = imageList[page]
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                ) {
                    Image(painter = painterResource(
                        id = newKids.imgUri
                    ),
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.height(140.dp)
                    )

                    Column(modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(15.dp)
                    ) {

                        Text(
                            text = newKids.title,
                            style = MaterialTheme.typography.h6,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        val ratingBar = RatingBar(
                            LocalContext.current, null,
                        ).apply {
                            rating = newKids.rating.toFloat()
                            progressDrawable.setColorFilter(
                                android.graphics.Color.parseColor("#FF0000"),
                                PorterDuff.Mode.SRC_ATOP
                            )
                        }

//                        AndroidView(factory ={ratingBar},
//                            modifier = Modifier.padding(0.dp,8.dp,0.dp,0.dp)
//                        )
                        Text(
                            text = newKids.desc,
                            style = MaterialTheme.typography.body2,
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(0.dp,8.dp,0.dp,0.dp)
                        )


                    }

                }


            }

        }
        //Horizontal dot indicator
        HorizontalPagerIndicator(
            pagerState = pagerState,modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

    }

}