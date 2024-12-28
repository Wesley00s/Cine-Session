package com.example.cine.session.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cine.session.core.network.util.isScreenLandscape
import com.example.cine.session.data.model.MovieInfo
import com.example.cine.session.ui.theme.AppTypography
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatedCarousel(
    modifier: Modifier = Modifier,
    movies: List<MovieInfo>,
    onMovieClick: (MovieInfo) -> Unit
) {
    val animatedScale = remember { Animatable(1f) }
    val isLandscape = isScreenLandscape()
    val carouselState = rememberCarouselState { movies.size }

    LaunchedEffect(Unit) {
        while (true) {
            animatedScale.animateTo(
                targetValue = 1.1f,
                animationSpec = tween(durationMillis = 5000, easing = FastOutSlowInEasing)
            )
            animatedScale.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 5000, easing = FastOutSlowInEasing)
            )
        }
    }

    LaunchedEffect(carouselState) {
        while (true) {
            delay(10000)
            carouselState.animateScrollBy(
                value = if (isLandscape) 2200f else 1000f,
                animationSpec = tween(durationMillis = 500, easing = EaseInOut)
            )
        }
    }

    HorizontalMultiBrowseCarousel(
        state = carouselState,
        preferredItemWidth = if (isLandscape) 800.dp else 500.dp,
        modifier = modifier,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(0.dp)
    ) { index ->
        Box(
            modifier = Modifier
                .height(if (isLandscape) 400.dp else 400.dp)
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        onMovieClick(movies[index])
                    }
                )
        ) {
            ImageFormat(
                path = "https://image.tmdb.org/t/p/original${movies[index].backdropPath}",
                isLandscape = isLandscape,
                modifier = Modifier
                    .fillMaxSize()
                    .maskClip(shape = MaterialTheme.shapes.small)
                    .graphicsLayer(
                        scaleX = animatedScale.value,
                        scaleY = animatedScale.value
                    )
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .offset(y = -(50).dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "POPULAR", style = AppTypography.headlineSmall.copy(
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                movies[index].title?.let {
                    Text(
                        text = it, style = AppTypography.headlineMedium.copy(
                            fontWeight = FontWeight.Black
                        )
                    )
                }
            }
        }
    }
}


