package com.halilkrkn.chatchef.presentation.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.halilkrkn.chatchef.R
import com.halilkrkn.chatchef.ui.theme.ColorButton
import com.halilkrkn.chatchef.ui.theme.MessageBubbleColor

/*
UserChatMessage(text = "message content", horizontalAlignment = Alignment.End)
*/

@Composable
fun UserChatMessage(
    modifier: Modifier = Modifier,
    text: String,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Card(
            shape = RoundedCornerShape(
                topStart = 48f,
                topEnd = 48f,
                bottomStart = 48f,
                bottomEnd = 0f
            ),
            modifier = modifier
                .padding(vertical = 4.dp)
                .widthIn(max = screenWidth * 0.7f),
            colors = CardDefaults.cardColors(ColorButton)
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(16.dp),
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Start,
                lineHeight = 20.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CustomPreview() {
    UserChatMessage(text = "message content", horizontalAlignment = Alignment.End)
}