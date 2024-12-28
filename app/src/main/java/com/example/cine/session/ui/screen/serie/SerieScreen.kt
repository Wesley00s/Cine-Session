package com.example.cine.session.ui.screen.serie

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
import com.example.cine.session.data.model.SerieInfo
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
fun SerieScreen(
    modifier: Modifier = Modifier,
    serieId: Int,
    uiState: SerieUiState,
    onEvent: (SerieUiEvent) -> Unit,
    onNavigateToSerie: (SerieInfo) -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(true) {
        onEvent(SerieUiEvent.LoadSerie(serieId))
        onEvent(SerieUiEvent.LoadSimilarSeries(serieId, 1))
    }

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
                    path = if (uiState.serie.backdropPath.isNullOrEmpty())
                        uiState.serie.posterPath.toString()
                    else uiState.serie.backdropPath.toString(),
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
                            ).format(uiState.serie.voteAverage ?: 0.0),
                            style = AppTypography.headlineMedium,
                            color = Color.White
                        )
                    }
                    Text(
                        text = uiState.serie.name.toString(),
                        style = AppTypography.displaySmall,
                        color = Color.White
                    )

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        overflow = FlowRowOverflow.Visible
                    ) {
                        val year = uiState.serie.firstAirDate.toString().take(4)
                        TagInfo(tag = year)

                        uiState.serie.genres?.forEach {
                            TagInfo(tag = it.name)
                        }

                        uiState.serie.originCountry?.forEach {
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

                        if (!uiState.serie.homepage.isNullOrEmpty()) {
                            CustomButton(
                                modifier = Modifier.width(180.dp),
                                borderRadius = 100,
                                text = "Watch Now",
                                active = true,
                                onClick = {
                                    Intent(Intent.ACTION_VIEW).apply {
                                        val intent = Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse(uiState.serie.homepage.toString())
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


                    Text(
                        text = uiState.serie.overview.toString(),
                        style = AppTypography.bodyLarge,
                        color = Color.LightGray
                    )
                }

                if (!uiState.similarSeries.isNullOrEmpty()) {
                    HorizontalList(
                        modifier = Modifier
                            .absoluteOffset(y = if (isLandscape) (-100).dp else (-70).dp),
                        items = uiState.similarSeries,
                        text = "Similar Movies",
                        onItemRender = { serie ->
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/original${serie.posterPath}",
                                contentDescription = "Poster",
                                modifier = Modifier.fillMaxSize()
                            )
                        },
                        onItemClick = { serie ->
                            onNavigateToSerie(serie)
                        }
                    )
                }
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