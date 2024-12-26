package com.example.cine.session.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cine.session.R

@Composable
fun ImageButton(
    modifier: Modifier = Modifier,
    image: Int,
    activeImage: Int = 0,
    onClick: (Boolean) -> Unit,
    toggleEnabled: Boolean = false
) {
    var isActive by remember { mutableStateOf(false) }

    Button(
        onClick = {
            if (toggleEnabled){
                isActive = !isActive
            }
            onClick(isActive)
        },
        modifier = modifier.size(50.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Unspecified,
            containerColor = Color.Transparent,

        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            painter = painterResource(if (isActive) activeImage else image),
            contentDescription = "Auxiliary button",
        )
    }
}

@Preview
@Composable
private fun Prev() {
    ImageButton(image = R.drawable.ic_favorite, activeImage = R.drawable.ic_favorite_active, onClick = {})
}