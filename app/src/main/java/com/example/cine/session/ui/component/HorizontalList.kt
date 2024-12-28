package com.example.cine.session.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cine.session.ui.theme.AppTypography

@Composable
fun <T> HorizontalList(
    modifier: Modifier = Modifier,
    items: List<T>,
    text: String,
    onItemRender: @Composable (T) -> Unit,
    onItemClick: (T) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = text, style = AppTypography.headlineMedium)
            Text(text = "View All", style = AppTypography.labelLarge)
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = items
            ) { item ->
                ItemCard(
                    item = item,
                    onClick = { onItemClick(it) }
                ) {
                    onItemRender(it)
                }
            }
        }
    }
}
