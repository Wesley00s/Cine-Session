package com.example.cine.session.ui.screen.movie

import android.content.Intent
import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.cine.session.R
import com.example.cine.session.core.network.util.isScreenLandscape
import com.example.cine.session.core.network.util.minutesToHours
import com.example.cine.session.data.model.MovieInfo
import com.example.cine.session.ui.component.CustomButton
import com.example.cine.session.ui.component.HorizontalList
import com.example.cine.session.ui.component.ImageButton
import com.example.cine.session.ui.component.ImageFormat
import com.example.cine.session.ui.component.TagInfo
import com.example.cine.session.ui.theme.AppTypography
import com.example.cine.session.ui.theme.Primary
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieScreen(
    modifier: Modifier = Modifier,
    movieId: Int,
    uiState: MovieUiState,
    onEvent: (MovieUiEvent) -> Unit,
    onNavigateToMovie: (MovieInfo) -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(true) {
        onEvent(MovieUiEvent.LoadMovie(movieId))
        onEvent(MovieUiEvent.LoadSimilarMovies(movieId, 1))
    }

    Log.d("MovieScreenDetails", "Response:" + uiState.movie.toString())

    val isLandscape = isScreenLandscape()
    val scrollState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(Primary),
            state = scrollState
        ) {
            item {
                ImageFormat(
                    modifier = modifier,
                    path = if (uiState.movie.backdropPath.isNullOrEmpty())
                                uiState.movie.posterPath.toString()
                           else uiState.movie.backdropPath.toString(),
                    isLandscape = isLandscape
                )

                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .offset(y = if (isLandscape) (-250).dp else (-150).dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_imdb_logo),
                            contentDescription = "IMDb Logo"
                        )
                        Text(
                            text = DecimalFormat(
                                "#.##",
                                DecimalFormatSymbols(Locale.US)
                            ).format(uiState.movie.voteAverage ?: 0.0),
                            style = AppTypography.headlineMedium,
                            color = Color.White
                        )
                    }
                    Text(
                        text = uiState.movie.title.toString(),
                        style = AppTypography.displaySmall,
                        color = Color.White
                    )

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        overflow = FlowRowOverflow.Visible
                    ) {
                        val year = uiState.movie.releaseDate.toString().take(4)
                        TagInfo(tag = year)

                        uiState.movie.genres?.forEach {
                            TagInfo(tag = it.name)
                        }

                        uiState.movie.originCountry?.forEach {
                            TagInfo(tag = it)
                        }
                    }

                    Row(
                        modifier = Modifier
                            .width(390.dp)
                            .defaultMinSize(700.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        if (!uiState.movie.homepage.isNullOrEmpty()) {
                            CustomButton(
                                modifier = Modifier.width(180.dp),
                                borderRadius = 100,
                                text = "Watch Now",
                                active = true,
                                onClick = {
                                    Intent(Intent.ACTION_VIEW).apply {
                                        val intent = Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse(uiState.movie.homepage.toString())
                                        )
                                        context.startActivity(intent)
                                    }
                                }
                            )
                        } else {
                            CustomButton(
                                modifier = Modifier.width(180.dp),
                                borderRadius = 100,
                                text = "Not Available",
                                active = false,
                                onClick = {}
                            )
                        }

                        ImageButton(
                            image = R.drawable.ic_favorite,
                            activeImage = R.drawable.ic_favorite_active,
                            toggleEnabled = true,
                            onClick = {})
                        ImageButton(image = R.drawable.ic_download, onClick = {})
                        ImageButton(image = R.drawable.ic_share, onClick = {})
                    }

                    Spacer(modifier = Modifier)

                    if (uiState.movie.runtime != null) {
                        Text(
                            text = "•${minutesToHours(uiState.movie.runtime)}",
                            style = AppTypography.headlineSmall,
                            color = Color.White
                        )
                    }

                    Text(
                        text = uiState.movie.overview.toString(),
                        style = AppTypography.bodyLarge,
                        color = Color.LightGray
                    )
                }

                if (!uiState.similarMovies.isNullOrEmpty()) {
                    HorizontalList(
                        modifier = Modifier
                            .absoluteOffset(y = if (isLandscape) (-100).dp else (-70).dp),
                        items = uiState.similarMovies,
                        text = "Similar Movies",
                        onItemRender = { movie ->
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/original${movie.posterPath}",
                                contentDescription = "Poster",
                                modifier = Modifier.fillMaxSize()
                            )
                        },
                        onItemClick = { movie ->
                            onNavigateToMovie(movie)
                        }
                    )
                }
                Log.d("SimilarMovies", "Response:" + uiState.similarMovies.toString())
            }
        }
        ImageButton(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 32.dp)
                .align(Alignment.TopStart)
                .size(40.dp),
            image = R.drawable.ic_back,
            onClick = { onBackClick() }
        )
    }
}



