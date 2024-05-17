package com.halilkrkn.chatchef.presentation.components

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.tooling.preview.Preview




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(isNotificationOn:Boolean=false,onBackClick: () -> Unit,notificationClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "") },
        navigationIcon = {

        },
        actions = {


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