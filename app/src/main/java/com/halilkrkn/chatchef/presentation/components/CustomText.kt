package com.halilkrkn.chatchef.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.halilkrkn.chatchef.R

@Composable
fun CustomTitleText(text: String) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color.Red, Color.Blue)
    )
    Text(
        text = text,

        style = TextStyle(
            fontSize = 35.sp,
            brush = gradientBrush
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp),
        textAlign = TextAlign.Start
    )
}

@Composable
fun CustomSubTitleText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 35.sp,
            color = colorResource(id = R.color.third_text_color)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp),
        textAlign = TextAlign.Start
    )
}

