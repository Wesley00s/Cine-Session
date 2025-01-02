package com.example.cine.session.ui.screen.serie.season

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cine.session.data.model.EpisodeInfo
import com.example.cine.session.ui.component.EpisodeCardList
import com.example.cine.session.ui.theme.Primary
import com.example.cine.session.ui.theme.Quaternary

@Composable
fun SeasonScreen(
    modifier: Modifier = Modifier,
    serieId: Int,
    uiState: SeasonUiState,
    onEvent: (SeasonUiEvent) -> Unit
) {
    LaunchedEffect(true) {
        onEvent(SeasonUiEvent.LoadSeason(serieId, 1))
    }


    Log.d("SeasonScreen_", "Season: ${uiState.season}")
    val episodes = uiState.season?.episodes?.map {
        EpisodeInfo(
            id = it.id,
            name = it.name,
            overview = it.overview,
            airDate = it.airDate,
            episodeNumber = it.episodeNumber,
            seasonNumber = it.seasonNumber,
            stillPath = it.stillPath,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            runtime = it.runtime
        )
    } ?: emptyList()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Primary)
    ) {
        if (episodes.isNotEmpty()) {
            EpisodeCardList(
                modifier = Modifier.fillMaxSize(),
                episodes = episodes,
                onEpisodeClick = { }
            )

        } else {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                color = Quaternary
            )
        }
    }
}
