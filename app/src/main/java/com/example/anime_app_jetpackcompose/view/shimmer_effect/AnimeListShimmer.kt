package com.example.anime_app_jetpackcompose.view.shimmer_effect

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp



@Composable
fun AnimeListShimmer() {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemCount = 10

    Row(modifier = Modifier.padding(12.dp)
    ) {
        for(i in 0..itemCount) {
            Box(modifier = Modifier.padding(end = 10.dp)) {
                Column(modifier = Modifier,
                    verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                    Box(
                        modifier = Modifier.padding(8.dp)
                            .clip(shape = RoundedCornerShape(size = 10.dp))
                            .height(240.dp)
                            .width(160.dp)
                            .background(color = Color.LightGray)
                            .shimmerLoadingAnimation()
                    )
                    {
                        Column(modifier = Modifier.padding(4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            Box(
                                modifier = Modifier.padding(16.dp)
                                    .clip(RoundedCornerShape(size = 5.dp))
                                    .height(160.dp)
                                    .width(120.dp)
                                    .background(color = Color.LightGray)
                                    .shimmerLoadingAnimation()
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(size = 5.dp))
                                    .width(screenWidth / 2)
                                    .height(18.dp)
                                    .background(color = Color.LightGray)
                                    .shimmerLoadingAnimation()
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(size = 5.dp))
                                    .width(screenWidth /4)
                                    .height(22.dp)
                                    .background(color = Color.LightGray)
                                    .shimmerLoadingAnimation()
                            )
                        }

                    }
                }
            }
        }
    }
}
