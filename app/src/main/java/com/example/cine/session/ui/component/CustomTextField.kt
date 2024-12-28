package com.example.cine.session.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import com.example.cine.session.ui.theme.Primary
import com.example.cine.session.ui.theme.Secondary
import com.example.cine.session.ui.theme.Tertiary

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    label: String,
    value: String,
    icon: Int
) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .border(
                0.5.dp,
                Tertiary,
                RoundedCornerShape(100)
            ),
        shape = RoundedCornerShape(100),
        label = {
            Text(text = label, color = Color.Gray)
        },
        leadingIcon = {
            Icon(painter = painterResource(id = icon), contentDescription = null, tint = Color.Gray)
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.LightGray,
            focusedContainerColor = Secondary,
            unfocusedContainerColor = Secondary,
            unfocusedTextColor = Color.LightGray,

            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Preview
@Composable
private fun Prev() {
    var fname by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CustomTextField(
            onValueChange = {
                fname = it
            },
            label = "First Name",
            value = fname,
            icon = R.drawable.ic_person
        )
        CustomTextField(
            onValueChange = {
                fname = it
            },
            label = "First Name",
            value = fname,
            icon = R.drawable.ic_person
        )
        CustomTextField(
            onValueChange = {
                fname = it
            },
            label = "First Name",
            value = fname,
            icon = R.drawable.ic_person
        )
        CustomTextField(
            onValueChange = {
                fname = it
            },
            label = "First Name",
            value = fname,
            icon = R.drawable.ic_person
        )
    }
}