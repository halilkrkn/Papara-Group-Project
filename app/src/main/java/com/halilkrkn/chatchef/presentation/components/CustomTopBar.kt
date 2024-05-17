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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.halilkrkn.chatchef.R

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
        modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 30.dp),
        title = { Text(text = "") },
        navigationIcon = {


            Box(
                modifier = Modifier
                    .size(50.dp, 50.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(colorResource(id = R.color.top_app_bar_icon_bg))
                    .clickable {
                        onBackClick()
                        /***
                         Back Button Transactions
                         ***/
                    }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.left_arrow),
                    tint = colorResource(id = R.color.top_app_bar_icon_fg),
                    contentDescription = "Back",
                    modifier = Modifier.align(Alignment.Center)
                )
            }


        },
        actions = {
            Box(
                modifier = Modifier
                    .size(50.dp, 50.dp)
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
                    imageVector = if (isNotificationOn) {
                        ImageVector.vectorResource(id = R.drawable.notification_true)
                    } else {
                        ImageVector.vectorResource(id = R.drawable.notification_false)
                    },
                    tint = colorResource(id = R.color.top_app_bar_icon_fg),
                    contentDescription = "Back",
                    modifier = Modifier.align(Alignment.Center),

                )
            }

        }
    )
}

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