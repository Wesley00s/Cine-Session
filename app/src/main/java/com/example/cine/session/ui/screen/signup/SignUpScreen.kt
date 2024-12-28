package com.example.cine.session.ui.screen.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cine.session.R
import com.example.cine.session.core.network.util.isScreenLandscape
import com.example.cine.session.ui.component.CustomButton
import com.example.cine.session.ui.component.CustomTextField
import com.example.cine.session.ui.component.ImageButton
import com.example.cine.session.ui.component.ImageFormat
import com.example.cine.session.ui.theme.AppTypography
import com.example.cine.session.ui.theme.Primary

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    uiState: SignUpUiState,
    onEvent: (SignUpUiEvent) -> Unit,
    onNavigateToHome: () -> Unit,
    onBackClick: () -> Unit

) {
    val context = LocalContext.current
    val scrollState = rememberLazyListState()
    val isLandscape = isScreenLandscape()

    LazyColumn(
        modifier = modifier
            .background(Primary)
            .fillMaxSize(),
        state = scrollState,
    ) {
        item {
            Box(modifier = Modifier.fillMaxSize()) {



                ImageFormat(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxSize(),
                    image = R.drawable.bg_login_head
                )

                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .align(Alignment.Center)
                        .offset(y = if (isLandscape) (-150).dp else 0.dp)
                        .fillMaxSize(if (isLandscape) 0.5f else 1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        modifier = Modifier
                            .padding(top = 50.dp)
                            .shadow(
                                elevation = 50.dp,
                                spotColor = Color.Black,
                                ambientColor = Color.Black
                            )
                            .size(200.dp),
                        painter = painterResource(id = R.drawable.ic_logo_text),
                        contentDescription = "Logo",
                    )

                    Text(
                        modifier = Modifier
                            .padding(bottom = if (isLandscape) 24.dp else 42.dp)
                            .shadow(
                                elevation = 50.dp,
                                spotColor = Color.Black,
                                ambientColor = Color.Black,
                            ),
                        text = "Sign Up",
                        style = AppTypography.displaySmall.copy(
                            textAlign = TextAlign.Center,
                            color = Color.LightGray,
                            fontWeight = FontWeight.ExtraBold
                        )

                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {

                        CustomTextField(
                            onValueChange = {},
                            label = "First Name",
                            value = "",
                            icon = R.drawable.ic_person,
                        )

                        CustomTextField(
                            onValueChange = {},
                            label = "Last Name",
                            value = "",
                            icon = R.drawable.ic_person,
                        )

                        CustomTextField(
                            onValueChange = {},
                            label = "Email",
                            value = "",
                            icon = R.drawable.ic_email,
                        )

                        CustomTextField(
                            onValueChange = {},
                            label = "Password",
                            value = "",
                            icon = R.drawable.ic_passkey,
                        )

                        CustomTextField(
                            onValueChange = {},
                            label = "Confirm Password",
                            value = "",
                            icon = R.drawable.ic_passkey,
                        )


                        CustomButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Sign Up",
                            borderRadius = 15,
                            active = true,
                            onClick = onNavigateToHome
                        )
                    }
                }

                ImageButton(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 32.dp)
                        .align(Alignment.TopStart)
                        .size(40.dp),
                    image = R.drawable.ic_back,
                    onClick = { onBackClick() }
                )
            }
        }
    }
}