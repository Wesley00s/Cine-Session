package com.example.cine.session.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cine.session.data.model.MovieInfo
import com.example.cine.session.ui.theme.AppTypography

@Composable
fun VerticalListMovies(
    modifier: Modifier = Modifier,
    items: List<MovieInfo>,
    onNavigateToMovie: (MovieInfo) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            if (items.isNotEmpty()) {
                items.chunked(2).forEach { movie ->
                    Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        movie.forEach { movieInfo ->
                            MovieCard(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(bottom = 16.dp),
                                movieInfo = movieInfo,
                                onClick = {
                                    onNavigateToMovie(movieInfo)
                                }
                            )
                        }
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "No movies found",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Gray,
                        style = AppTypography.headlineMedium
                    )
                }
            }
        }
    }
}