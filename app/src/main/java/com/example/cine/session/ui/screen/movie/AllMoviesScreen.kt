package com.example.cine.session.ui.screen.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cine.session.data.model.MovieInfo
import com.example.cine.session.data.remote.response.movie.ListMoviesResponse
import com.example.cine.session.ui.component.VerticalListMovies
import com.example.cine.session.ui.theme.AppTypography

@Composable
fun AllMoviesScreen(
    modifier: Modifier = Modifier,
    listMoviesResponse: ListMoviesResponse,
    onNavigateToMovie: (MovieInfo) -> Unit,
    onBackClick: () -> Unit
) {
    var movieInfo by remember { mutableStateOf<List<MovieInfo>?>(null) }

    movieInfo = listMoviesResponse.let {
        it.results.let { results ->
            results.map { result ->
                MovieInfo(
                    id = result.id,
                    posterPath = result.posterPath,
                    title = result.title,
                    overview = result.overview,
                    releaseDate = result.releaseDate,
                    voteAverage = result.voteAverage,
                    voteCount = result.voteCount,
                    backdropPath = result.backdropPath,
                    originalTitle = result.originalTitle,
                    popularity = result.popularity
                )
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(.8f),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                    Text(text = "All Movies", style = AppTypography.headlineMedium)
                }

//                Text(text = "Page $page", style = AppTypography.bodyMedium, modifier = Modifier.weight(.2f))
            }

        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (!movieInfo.isNullOrEmpty()) {
                VerticalListMovies(
                    items = movieInfo.orEmpty(),
                    onNavigateToMovie = onNavigateToMovie,
                    modifier = Modifier.fillMaxSize()
                )
            }

//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp)
//                    .align(Alignment.BottomCenter),
//                horizontalArrangement = Arrangement.SpaceAround
//            ) {
////                FloatingActionButton(
////                    onClick = {
////                        if (page > 1) {
////                            page -= 1
////                            onEvent(AllMoviesUiEvent.LoadPopularMovies(page))
////                            onEvent(AllMoviesUiEvent.LoadTopRatedMovies(page))
////                            onEvent(AllMoviesUiEvent.LoadUpcomingMovies(page))
////                        }
////                    },
////                    containerColor = Secondary,
////                    contentColor = Color.White,
////                    shape = RoundedCornerShape(50.dp),
////                    elevation = FloatingActionButtonDefaults.elevation(50.dp),
////                    modifier = Modifier
////                        .weight(.4f)
////                        .padding(horizontal = 8.dp)
////                        .shadow(
////                            elevation = 5.dp,
////                            shape = RoundedCornerShape(50.dp),
////                            ambientColor = Color.Black,
////                            spotColor = Color.Black
////                        )
////                ) {
////                    Row(
////                        modifier = Modifier.fillMaxWidth(),
////                        verticalAlignment = Alignment.CenterVertically,
////                        horizontalArrangement = Arrangement.SpaceEvenly
////                    ) {
////                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Previous")
////                        Text(
////                            text = "Previous page",
////                            color = Color.White,
////                            style = AppTypography.bodyMedium
////                        )
////                    }
////                }
////
////                FloatingActionButton(
////                    onClick = {
////                        page += 1
////                        onEvent(AllMoviesUiEvent.LoadPopularMovies(page))
////                        onEvent(AllMoviesUiEvent.LoadTopRatedMovies(page))
////                        onEvent(AllMoviesUiEvent.LoadUpcomingMovies(page))
////                    },
////                    containerColor = Secondary,
////                    contentColor = Color.White,
////                    shape = RoundedCornerShape(50.dp),
////                    elevation = FloatingActionButtonDefaults.elevation(0.dp),
////                    modifier = Modifier
////                        .weight(.4f)
////                        .padding(horizontal = 8.dp)
////                        .shadow(
////                            elevation = 5.dp,
////                            shape = RoundedCornerShape(50.dp),
////                            ambientColor = Color.Black,
////                            spotColor = Color.Black
////                        )
////                ) {
////                    Row(
////                        modifier = Modifier.fillMaxWidth(),
////                        verticalAlignment = Alignment.CenterVertically,
////                        horizontalArrangement = Arrangement.SpaceEvenly
////                    ) {
////                        Text(
////                            text = "Next page",
////                            color = Color.White,
////                            style = AppTypography.bodyMedium
////                        )
////                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next")
////                    }
////                }
//            }

        }
    }
}