package com.halilkrkn.chatchef.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomContainer(
    modifier: Modifier = Modifier,
    sendButtonClicked: (String) -> Unit = {}
) {
    var value by rememberSaveable { mutableStateOf("") }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 6.dp)
    ) {
        CustomTextField(
            modifier = Modifier
                .padding(start = 4.dp)
                .weight(3f),
            value = value
        ) {
            value = it
        }
        CustomButton(
            modifier = Modifier
                .height(54.dp)
                .padding(start = 4.dp, end = 4.dp)
                .weight(0.60f)
        ) {
            sendButtonClicked(value)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FooterPreview() {
    Surface {
        BottomContainer{

        }
    }
}
