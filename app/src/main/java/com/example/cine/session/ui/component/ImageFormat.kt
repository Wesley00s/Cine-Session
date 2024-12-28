package com.example.cine.session.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.cine.session.R
import com.example.cine.session.ui.theme.Primary
import com.example.cine.session.ui.theme.Primary20
import com.example.cine.session.ui.theme.Primary40
import com.example.cine.session.ui.theme.Primary70

@Composable
fun ImageFormat(
    modifier: Modifier = Modifier,
    path: String? = null,
    isRemote: Boolean = true,
    image: Int? = null,
    isLandscape: Boolean = false
) {
    Box(
        modifier = if (isLandscape) {
            modifier
                .fillMaxSize()
                .size(500.dp)
        } else {
            modifier.size(390.dp, 400.dp)
        }
    ) {
        if (isRemote && path != null) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/original${path}",
                contentDescription = "Background",
                modifier = Modifier
                    .matchParentSize()
                    .graphicsLayer { clip = true },
                contentScale = ContentScale.Crop
            )
        } else if (image != null) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Background",
                modifier = Modifier
                    .matchParentSize()
                    .graphicsLayer { clip = true },
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Primary,
                            Primary70,
                            Primary40,
                            Primary20,
                            Primary40,
                            Primary70,
                            Primary,
                            Primary,
                        )
                    )
                )
        )
    }
}

@Preview
@Composable
private fun Prev() {
    ImageFormat(
        image = R.drawable.bg_login_head,
    )
}