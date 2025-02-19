package com.example.cine.session.ui.component

import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cine.session.R
import com.example.cine.session.data.model.MovieInfo
import com.example.cine.session.data.remote.response.movie.ListMoviesResponse
import com.example.cine.session.data.remote.response.movie.MovieItemResponse
import com.example.cine.session.ui.theme.AppTypography
import com.example.cine.session.ui.theme.Tertiary
import java.util.Locale

@Composable
fun MovieHorizontalList(
    modifier: Modifier = Modifier,
    movies: List<MovieInfo>,
    text: String,
    onItemClick: (MovieInfo) -> Unit,
    onViewAllMovies: (ListMoviesResponse) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = text, style = AppTypography.labelLarge)
            Text(
                text = "View All",
                color = Tertiary,
                style = AppTypography.labelLarge,
                modifier = Modifier
                    .clickable {
                        val response = ListMoviesResponse(
                            results = movies.map {
                                MovieItemResponse(
                                    id = it.id ?: 0,
                                    posterPath = it.posterPath,
                                    title = it.title ?: "",
                                    overview = it.overview ?: "",
                                    releaseDate = it.releaseDate ?: "",
                                    voteAverage = it.voteAverage ?: 0.0,
                                    voteCount = it.voteCount ?: 0,
                                    backdropPath = it.backdropPath ?: "",
                                    originalTitle = it.originalTitle ?: "",
                                    popularity = it.popularity ?: 0.0,
                                    adult = false,
                                    genreIds = it.genresIds ?: emptyList(),
                                    originalLanguage = "",
                                    video = false,
                                )

                            }
                        )
                        onViewAllMovies(response)
                    }
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = movies
            ) { item ->
                Box(modifier = Modifier) {
                    MovieCard(
                        movieInfo = item,
                        onClick = { onItemClick(it) }
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.TopStart)
                            .padding(4.dp)
                            .shadow(
                                elevation = 10.dp,
                                spotColor = Color.Black
                            )
                            .background(
                                Color.Gray.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(50.dp)
                            ),
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.ic_imdb_logo),
                                contentDescription = "IMDb Logo"
                            )
                            Text(

                                text = DecimalFormat(
                                    "#.##",
                                    DecimalFormatSymbols(Locale.US)
                                ).format(item.voteAverage ?: 0.0),
                                style = AppTypography.bodyMedium,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}
