package com.example.cine.session.ui.screen.splash

import android.widget.ProgressBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cine.session.R
import com.example.cine.session.ui.theme.Primary
import com.example.cine.session.ui.theme.Tertiary
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavigateToScreen: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(1000)
        onNavigateToScreen()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Primary),
    ) {
        Image(modifier = Modifier.size(150.dp).align(Alignment.Center),
            painter = painterResource(id = R.drawable.ic_logo), contentDescription = "Logo")

        CircularProgressIndicator(
            modifier = Modifier.size(30.dp).align(Alignment.BottomCenter).offset(y = (-50).dp),
            color = Tertiary
        )
    }

}

@Preview
@Composable
private fun Prev() {
    SplashScreen(onNavigateToScreen = {})
}