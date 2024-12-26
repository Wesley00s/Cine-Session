package com.example.cine.session.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.cine.session.data.model.MovieInfo

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movieInfo: MovieInfo,
    onClick: (MovieInfo) -> Unit
) {

    Card(
        modifier = modifier
            .width(150.dp)
            .height(225.dp)
            .clickable { onClick(movieInfo) },
        elevation = CardDefaults
            .cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/original${movieInfo.posterPath}",
            contentDescription = "Poster",
            modifier = Modifier.fillMaxSize()
        )
    }
}