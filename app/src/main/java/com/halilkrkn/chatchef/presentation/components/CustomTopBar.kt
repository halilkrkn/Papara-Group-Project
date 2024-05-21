package com.halilkrkn.chatchef.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.halilkrkn.chatchef.R
import com.halilkrkn.chatchef.ui.theme.MainBackgroundColor

/***
 Example Use:

    CustomTopAppBar(onBackClick = {}, notificationClick = {})
    -------------------------OR------------------------------
    CustomTopAppBar(isNotificationOn=true, onBackClick = {}, notificationClick = {})

    isNotificationOn is optional. (Default value is false.)
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(isNotificationOn:Boolean=false,onBackClick: () -> Unit,notificationClick: () -> Unit) {
    TopAppBar(
        modifier = Modifier
            .padding(start = 10.dp, end = 20.dp),
        title = {  Text(
            text = "ChatChef",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        ) },
        colors = TopAppBarDefaults.topAppBarColors(MainBackgroundColor),
        actions = {
            Box(
                modifier = Modifier
                    .size(45.dp, 45.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(colorResource(id = R.color.top_app_bar_icon_bg))
                    .clickable {
                        notificationClick()
                        /***
                        Notification Button Transactions
                         ***/
                    }
            ) {
                Icon(
                   imageVector = ImageVector.vectorResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                    tint = colorResource(id = R.color.top_app_bar_icon_fg),
                    contentDescription = "Back",
                    modifier = Modifier.align(Alignment.Center),

                )
            }

        }
    )
}
/*Row(
                modifier = Modifier
                    .padding(start = 10.dp, bottom = 10.dp, top = 5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ChatGpt",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }*/

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun CustomTopAppBarPreview() {
    Scaffold(
        topBar = { CustomTopAppBar(onBackClick = {},notificationClick = {}) },
        content = {
        }

    )
}