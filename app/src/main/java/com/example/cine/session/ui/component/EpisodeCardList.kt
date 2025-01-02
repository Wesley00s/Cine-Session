package com.example.cine.session.ui.component

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cine.session.data.model.EpisodeInfo

@Composable
fun EpisodeCardList(
    modifier: Modifier = Modifier,
    episodes: List<EpisodeInfo>,
    onEpisodeClick: (EpisodeInfo) -> Unit
) {

    Log.d("EpisodeCardList", "Episodes: $episodes")

    Column(
        modifier = modifier,

        ) {
        episodes.forEach { episode ->
            EpisodeCard(
                episode = episode,
                onEpisodeClick = { onEpisodeClick(episode) }
            )
        }
    }
}

@Preview
@Composable
private fun Prev() {
    EpisodeCardList(
        episodes = listOf(
            EpisodeInfo(
                id = 1,
                name = "Episode 1",
                overview = "Overview",
                airDate = "2023-01-01",
                episodeNumber = 1,
                seasonNumber = 1,
                stillPath = "/iraDBIZNHvtK6GWCG2rHLGpPv2O.jpg",
                voteAverage = 8.5,
                voteCount = 100,
                runtime = 65
            ),
            EpisodeInfo(
                id = 1,
                name = "Episode 1",
                overview = "Overview",
                airDate = "2023-01-01",
                episodeNumber = 1,
                seasonNumber = 1,
                stillPath = "/iraDBIZNHvtK6GWCG2rHLGpPv2O.jpg",
                voteAverage = 8.5,
                voteCount = 100,
                runtime = 65
            ),
            EpisodeInfo(
                id = 1,
                name = "Episode 1",
                overview = "Overview",
                airDate = "2023-01-01",
                episodeNumber = 1,
                seasonNumber = 1,
                stillPath = "/iraDBIZNHvtK6GWCG2rHLGpPv2O.jpg",
                voteAverage = 8.5,
                voteCount = 100,
                runtime = 65
            ),
            EpisodeInfo(
                id = 1,
                name = "Episode 1",
                overview = "Overview",
                airDate = "2023-01-01",
                episodeNumber = 1,
                seasonNumber = 1,
                stillPath = "/iraDBIZNHvtK6GWCG2rHLGpPv2O.jpg",
                voteAverage = 8.5,
                voteCount = 100,
                runtime = 65
            ),
        ),
        onEpisodeClick = {}
    )
}