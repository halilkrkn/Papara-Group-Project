package com.halilkrkn.chatchef.presentation.LoginPage

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController


@Composable
fun LoginPage() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        val navController = rememberNavController()
    }
}
