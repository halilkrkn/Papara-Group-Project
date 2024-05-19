package com.halilkrkn.chatchef

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.halilkrkn.chatchef.presentation.MainScreen
import com.halilkrkn.chatchef.ui.theme.ChatChefTheme
import com.halilkrkn.chatchef.ui.theme.MainBackgroundColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatChefTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MainBackgroundColor
                ) {
                    MainScreen()
                }
            }
        }
    }
}