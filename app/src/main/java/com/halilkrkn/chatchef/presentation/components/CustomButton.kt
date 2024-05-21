package com.halilkrkn.chatchef.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.halilkrkn.chatchef.R
import com.halilkrkn.chatchef.ui.theme.ColorButton

@Composable
fun CustomButton(modifier: Modifier, sendButtonClicked: () -> Unit) {
    Button(
        onClick = {
            sendButtonClicked()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = ColorButton,
            contentColor = colorResource(id = R.color.top_app_bar_icon_bg)
        ),
        shape = CircleShape,
        contentPadding = PaddingValues(12.dp),
        content = {
            Icon(
                modifier = Modifier
                    .size(48.dp),
                painter = painterResource(R.drawable.ic_send),
                contentDescription = "",
//                tint = colorResource(id = R.color.top_app_bar_icon_fg)
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