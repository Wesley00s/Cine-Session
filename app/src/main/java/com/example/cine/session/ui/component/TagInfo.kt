package com.example.cine.session.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cine.session.ui.theme.AppTypography
import com.example.cine.session.ui.theme.Primary
import com.example.cine.session.ui.theme.Tertiary

@Composable
fun TagInfo(
    tag: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(
                width = 0.4.dp,
                color = Tertiary,
                shape = RoundedCornerShape(50.dp)
            )
            .background(
                color = Primary.copy(alpha = 0.5f),
                shape = RoundedCornerShape(50.dp)
            )
    ) {
        Text(
            text = tag,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp),
            color = Tertiary,
            style = AppTypography.labelMedium.copy(
                fontWeight = FontWeight.Light
            )
        )
    }
}

@Preview
@Composable
private fun TagInfoPrev() {
    TagInfo(tag = "2025")
}