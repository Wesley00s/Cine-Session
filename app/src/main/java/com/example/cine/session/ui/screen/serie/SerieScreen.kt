package com.example.cine.session.ui.screen.serie

import android.content.Intent
import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cine.session.R
import com.example.cine.session.core.network.util.isScreenLandscape
import com.example.cine.session.data.model.SerieInfo
import com.example.cine.session.ui.component.CustomButton
import com.example.cine.session.ui.component.ImageButton
import com.example.cine.session.ui.component.ImageFormat
import com.example.cine.session.ui.component.TagInfo
import com.example.cine.session.ui.screen.serie.season.SeasonScreen
import com.example.cine.session.ui.screen.serie.season.SeasonUiEvent
import com.example.cine.session.ui.screen.serie.season.SeasonViewModel
import com.example.cine.session.ui.theme.AppTypography
import com.example.cine.session.ui.theme.Primary
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SerieScreen(
    modifier: Modifier = Modifier,
    serieId: Int,
    seasonNumber: Int = 1,
    uiState: SerieUiState,
    seasonViewModel: SeasonViewModel,
    onEvent: (SerieUiEvent) -> Unit,
    seasonUiEvent: (SeasonUiEvent) -> Unit,
    onNavigateToSerie: (SerieInfo) -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val seasonUiState by seasonViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        onEvent(SerieUiEvent.LoadSerie(serieId))
        onEvent(SerieUiEvent.LoadSimilarSeries(serieId, 1))
        seasonUiEvent(SeasonUiEvent.LoadSeason(serieId, seasonNumber))
    }

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { uiState.serie.seasons?.size ?: 0 }
    )

    val isLandscape = isScreenLandscape()
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Log.d("_SerieScreen", "Serie: ${uiState.serie}")


    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Primary

    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize(),
                state = scrollState
            ) {
                item {
                    ImageFormat(
                        modifier = Modifier,
                        path = if (uiState.serie.backdropPath.isNullOrEmpty())
                            uiState.serie.posterPath.toString()
                        else uiState.serie.backdropPath.toString(),
                        isLandscape = isLandscape
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                            .offset(y = if (isLandscape) (-250).dp else (-150).dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
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
                }
                item {
                    val tabs = uiState.serie.seasons?.map { it.name } ?: emptyList()
                    val pages: List<@Composable () -> Unit> = uiState.serie.seasons?.map { season ->
                        {
                            SeasonScreen(
                                modifier = Modifier.fillMaxSize(),
                                serieId = serieId,
                                uiState = seasonUiState,
                                onEvent = {
                                    seasonUiEvent(
                                        SeasonUiEvent.LoadSeason(
                                            serieId,
                                            season.seasonNumber
                                        )
                                    )
                                }
                            )
                        }
                    } ?: listOf {
                        Text(
                            "No Seasons Available",
                            style = AppTypography.bodyLarge,
                            color = Color.Gray
                        )
                    }

                    TabLayout(
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(y = (-120).dp),
                        coroutineScope = coroutineScope,
                        tabs = tabs,
                        pages = pages,
                        pagerState = pagerState
                    )

                }
            }
            ImageButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 32.dp)
                    .align(Alignment.TopStart)
                    .size(40.dp),
                image = R.drawable.ic_back,
                onClick = {
                    uiState.serie.seasons = null
                    seasonUiState.season = null
                    onBackClick()
                }
            )
        }
    }
}