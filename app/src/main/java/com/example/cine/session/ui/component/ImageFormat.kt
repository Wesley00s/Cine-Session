package com.example.cine.session.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.cine.session.ui.theme.Primary
import com.example.cine.session.ui.theme.Primary0
import com.example.cine.session.ui.theme.Primary40
import com.example.cine.session.ui.theme.Primary70

@Composable
fun ImageFormat(
    modifier: Modifier = Modifier,
    backdropPath: String,
    isLandscape: Boolean = false
) {
    Box(
        modifier = if (isLandscape) {
            modifier.fillMaxSize().size(500.dp)
        } else {
            modifier.size(390.dp, 400.dp)
        }
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/original${backdropPath}",
            contentDescription = "Background",
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer { clip = true },
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Primary0,
                            Primary40,
                            Primary70,
                            Primary,
                        )
                    )
                )
        )
    }
}
