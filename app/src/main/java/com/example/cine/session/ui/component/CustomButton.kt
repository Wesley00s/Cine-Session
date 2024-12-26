package com.example.cine.session.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cine.session.ui.theme.AppTypography
import com.example.cine.session.ui.theme.Quaternary
import com.example.cine.session.ui.theme.Tertiary

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Unspecified,
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp),
        onClick = onClick
    ) {
        Box(
            modifier = modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Quaternary,
                            Tertiary,
                        )
                    )
                )
        ) {
            Text(
                modifier = modifier.padding(vertical = 16.dp, horizontal = 42.dp),
                text = text,
                color = Color.White,
                style = AppTypography.headlineSmall.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Preview
@Composable
private fun Prev() {
    CustomButton(text = "Watch Now", onClick = {})
}