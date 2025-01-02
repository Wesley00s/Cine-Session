package com.example.cine.session.ui.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.cine.session.R
import com.example.cine.session.core.network.util.minutesToHours
import com.example.cine.session.data.model.EpisodeInfo
import com.example.cine.session.ui.theme.AppTypography
import com.example.cine.session.ui.theme.Primary
import com.example.cine.session.ui.theme.Quaternary
import com.example.cine.session.ui.theme.Secondary

@Composable
fun EpisodeCard(
    modifier: Modifier = Modifier,
    episode: EpisodeInfo,
    onEpisodeClick: () -> Unit = {}

) {
    Log.d("EpisodeCard", "Episode: $episode")
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Secondary,
                        Primary,
                    ),
                    radius = 500f
                )
            )
            .clip(
                shape = RoundedCornerShape(20.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(modifier = modifier) {

            if(episode.stillPath.isNullOrEmpty()) {
                Image(
                    modifier = Modifier
                        .height(130.dp)
                        .padding(8.dp)
                        .clip(
                            shape = RoundedCornerShape(10.dp)
                        ),
                    painter = painterResource(id = R.drawable.ic_not_available),
                    contentDescription = "Not Available Poster"
                )
            } else {
                AsyncImage(
                    modifier = Modifier
                        .height(130.dp)
                        .padding(8.dp)
                        .clip(
                            shape = RoundedCornerShape(10.dp)
                        ),
                    model = "https://image.tmdb.org/t/p/w500${episode.stillPath}",
                    contentDescription = "Poster",
                    )
                Icon(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(20.dp)
                        .border(2.dp, Quaternary, RoundedCornerShape(10.dp))
                        .align(Alignment.Center)
                        .clickable { onEpisodeClick() },
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play",
                    tint = Quaternary,
                )
            }

        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = episode.name,
                modifier = Modifier,
                style = AppTypography.headlineSmall,
                color = Color.LightGray
            )
            episode.runtime.toString().let {
                Text(
                    text = try {
                        minutesToHours(it.toInt())
                    } catch (e: Exception) {
                        "00:00"
                    },
                    modifier = Modifier,
                    style = AppTypography.labelMedium,
                    color = Color.LightGray
                )
            }
        }
    }
}

@Preview
@Composable
private fun PRev() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        state = androidx.compose.foundation.lazy.rememberLazyListState()
    ) {
        item {
            EpisodeCard(
                episode = EpisodeInfo(
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
                )
            )
        }
    }
}