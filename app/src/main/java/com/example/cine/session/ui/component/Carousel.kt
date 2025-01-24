package com.example.cine.session.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.cine.session.core.network.util.isScreenLandscape
import kotlinx.coroutines.delay

@Composable
fun <T> Carousel(
    modifier: Modifier = Modifier,
    items: List<T>,
    content: @Composable BoxScope.(T) -> Unit
) {
    val isLandscape = isScreenLandscape()
    val pagerState = rememberPagerState(pageCount = { items.size })
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
                delay(10000)
                val nextPage = (pagerState.currentPage + 1) % items.size
                pagerState.animateScrollToPage(
                    page = nextPage,
                    animationSpec = tween(durationMillis = 500)
                )
            }
        }
    }

    Column(modifier = modifier) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            Box(
                modifier = Modifier
                    .height(if (isLandscape) 400.dp else 400.dp)
                    .fillMaxWidth()
            ) {
                content(items[page])
            }
        }

        PageIndicators(
            modifier = Modifier.offset(y = (-26).dp),
            totalItems = items.size,
            currentPage = pagerState.currentPage
        )
    }
}

@Composable
fun PageIndicators(
    totalItems: Int,
    currentPage: Int,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        repeat(totalItems) { index ->
            val isSelected = index == currentPage

            val width by animateDpAsState(
                targetValue = if (isSelected) 32.dp else 8.dp,
                animationSpec = tween(durationMillis = 500),
                label = ""
            )

            val color by animateColorAsState(
                targetValue = if (isSelected)
                    Color.Cyan.copy(alpha = 0.7f)
                else Color.Gray.copy(
                    alpha = 0.6f
                ),
                animationSpec = tween(durationMillis = 500),
                label = ""
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .height(8.dp)
                    .width(width)
                    .clip(RoundedCornerShape(6.dp))
                    .background(color)
            )
        }
    }
}
