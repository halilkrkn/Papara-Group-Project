package com.halilkrkn.chatchef.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.halilkrkn.chatchef.R

@Composable
fun CustomButton(modifier: Modifier, sendButtonClicked: () -> Unit) {
    Button(
        onClick = {
            sendButtonClicked()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        shape = CircleShape,
        contentPadding = PaddingValues(16.dp),
        content = {
            Icon(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(R.drawable.ic_send),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.secondary
            )
        },
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CustomButtonPreview() {
    Surface {
        CustomButton(Modifier) {

        }
    }
}