package com.example.cine.session.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cine.session.ui.theme.AppTypography
import com.example.cine.session.ui.theme.Primary
import com.example.cine.session.ui.theme.Quaternary
import com.example.cine.session.ui.theme.Secondary
import com.example.cine.session.ui.theme.Tertiary

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: Int? = null,
    borderRadius: Int = 15,
    colerfull: Boolean = true,
    active: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.border(1.dp, Quaternary, RoundedCornerShape(borderRadius.dp)),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Unspecified,
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp),
        enabled = active,
        shape = RoundedCornerShape(borderRadius.dp),
        elevation = ButtonDefaults.buttonElevation(10.dp),
        onClick = onClick
    ) {
        Box(
            modifier = if (active) {
                if (colerfull) {

                    Modifier
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Quaternary,
                                    Tertiary,
                                )
                            )
                        )
                        .fillMaxWidth()
                } else {
                    Modifier
                        .background(Secondary)
                        .fillMaxWidth()
                }
            } else {
                Modifier
                    .background(Color.Gray)
                    .fillMaxWidth()
            },
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().height(58.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                if (icon != null) {

                    Image(
                        modifier = Modifier.size(42.dp),
                        painter = painterResource(id = icon),
                        contentDescription = "Icon"
                    )
                }


                Text(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    text = text,
                    color = if (active) Color.White else Color.LightGray,
                    style = AppTypography.headlineSmall.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun Prev() {
    Column (verticalArrangement = Arrangement.spacedBy(16.dp)) {

        CustomButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Watch Now",
            onClick = {},
            active = true
        )
        CustomButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Watch Now",
            icon = com.example.cine.session.R.drawable.ic_google,
            onClick = {},
            colerfull = false,
            active = true
        )
    }
}