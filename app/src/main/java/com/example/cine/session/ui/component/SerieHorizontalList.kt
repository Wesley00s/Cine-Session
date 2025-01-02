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
import com.example.cine.session.data.model.SerieInfo
import com.example.cine.session.ui.theme.AppTypography
import com.example.cine.session.ui.theme.Tertiary
import java.util.Locale

@Composable
fun SerieHorizontalList(
    modifier: Modifier = Modifier,
    series: List<SerieInfo>,
    text: String,
    onItemClick: (SerieInfo) -> Unit,
    onViewAllSeries: (List<SerieInfo>) -> Unit
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
                        onViewAllSeries(series)
                    }
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = series
            ) { item ->
                Box(modifier = Modifier) {
                    SerieCard(
                        serieInfo = item,
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
                                Color.LightGray.copy(alpha = 0.3f),
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
