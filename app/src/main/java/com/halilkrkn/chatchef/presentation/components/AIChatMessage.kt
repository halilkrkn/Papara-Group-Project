package com.halilkrkn.chatchef.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.halilkrkn.chatchef.R
import com.halilkrkn.chatchef.ui.theme.ButtonBackgroundColor
import com.halilkrkn.chatchef.ui.theme.ColorButton1
import com.halilkrkn.chatchef.ui.theme.MessageChatBubbleColor
import com.halilkrkn.chatchef.ui.theme.TextPink

@Composable
fun AiChatMessage(
    modifier: Modifier = Modifier,
    message : String = "",
    fontWeight: FontWeight = FontWeight.SemiBold,
    fontSize : TextUnit = 13.sp,
    fontFamily: FontFamily = FontFamily.Default,
    loading : @Composable () -> Unit = { },
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = horizontalAlignment,
    ){
        Card(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(0.75f),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 15.dp,
                bottomEnd = 15.dp,
                bottomStart = 15.dp
            ),
            colors = CardDefaults.cardColors(colorResource(id = R.color.ai_chat_bubble))
        ) {
            Column(
                modifier = modifier
                    .padding(15.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    buildAnnotatedString {
                        append("Hello! ")
                        withStyle(
                            style = SpanStyle(
                                color = TextPink
                            )
                        ){
                            append("Emmanuel")
                        }
                        append(", I am ChatChef's AI. How can I help you?")
                    },
                    fontWeight = fontWeight,
                    fontSize = fontSize,
                    fontFamily = fontFamily,
                    textAlign = TextAlign.Start,
                    lineHeight = 20.sp
                )
                //loading()
            }
        }

    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun AiChatMessagePreview() {
    AiChatMessage()
}

