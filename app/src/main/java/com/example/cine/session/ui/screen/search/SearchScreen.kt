package com.example.cine.session.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cine.session.R
import com.example.cine.session.data.model.MovieInfo
import com.example.cine.session.data.model.SerieInfo
import com.example.cine.session.ui.component.CustomButton
import com.example.cine.session.ui.component.CustomTextField
import com.example.cine.session.ui.component.VerticalListMovies
import com.example.cine.session.ui.component.VerticalListSeries

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    uiState: SearchUiState,
    onEvent: (SearchUiEvent) -> Unit,
    onNavigateToMovie: (MovieInfo) -> Unit = {},
    onNavigateToSerie: (SerieInfo) -> Unit = {}
) {
    var query by remember { mutableStateOf("") }
    var isFilterMovies by remember { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(true) {
        focusRequester.requestFocus()
    }

    Column {
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .focusRequester(focusRequester),
            trailingIcon = {
                if (query.isNotEmpty())
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close", modifier = Modifier.clickable {
                        query = ""
                        onEvent(
                            if (isFilterMovies)
                                SearchUiEvent.SearchMovies(query, 1)
                            else
                                SearchUiEvent.SearchSeries(query, 1)
                        )

                    }.size(20.dp).padding(1.dp),

                    )
            },
            value = query,
            onValueChange = {
                query = it
                onEvent(
                    if (isFilterMovies)
                        SearchUiEvent.SearchMovies(query, 1)
                    else
                        SearchUiEvent.SearchSeries(query, 1)
                )
            },
            icon = R.drawable.ic_search,
            placeholder = "Search for movies or series"
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(bottom = 16.dp)
        ) {
            CustomButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(.5f),
                text = "Movies",
                colerfull = isFilterMovies,
                onClick = {
                    isFilterMovies = true
                    onEvent(
                        SearchUiEvent.SearchMovies(query, 1)
                    )
                }
            )

            CustomButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(.5f),
                text = "Series",
                colerfull = !isFilterMovies,
                onClick = {
                    isFilterMovies = false
                    onEvent(
                        SearchUiEvent.SearchSeries(query, 1)
                    )
                }
            )
        }

        if (isFilterMovies) {
            uiState.movies?.let { movies ->
                VerticalListMovies(
                    modifier = modifier.fillMaxSize(),
                    items = movies,
                    onNavigateToMovie = onNavigateToMovie
                )
            }
        } else {
            uiState.series?.let { series ->
                VerticalListSeries(
                    modifier = modifier.fillMaxSize(),
                    items = series,
                    onNavigateToSerie = onNavigateToSerie
                )
            }
        }
    }
}
