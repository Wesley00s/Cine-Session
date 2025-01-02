package com.example.cine.session.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.cine.session.core.network.util.isScreenLandscape
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> AnimatedCarousel(
    modifier: Modifier = Modifier,
    items: List<T>,
    onItemClick: (T) -> Unit,
    content: @Composable BoxScope.(T, Animatable<Float, AnimationVector1D>) -> Unit
) {
    val animatedScale = remember { Animatable(1f) }
    val isLandscape = isScreenLandscape()
    val carouselState = rememberCarouselState { items.size }
    val lifecycleOwner = LocalLifecycleOwner.current
    var isActive by remember { mutableStateOf(true) }


    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            isActive = event == Lifecycle.Event.ON_RESUME
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(isActive) {
        if (isActive) {
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
    }

    LaunchedEffect(isActive) {
        if (isActive) {
            while (true) {
                delay(10000)
                carouselState.animateScrollBy(
                    value = if (isLandscape) 2200f else 1000f,
                    animationSpec = tween(durationMillis = 500, easing = EaseInOut)
                )
            }
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
                .clickable {
                    onItemClick(items[index])
                }
        ) {
            Box(
                modifier = Modifier
                    .height(if (isLandscape) 400.dp else 400.dp)
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(items[index])
                    }
            ) {
                content(items[index], animatedScale)
            }
        }
    }
}


