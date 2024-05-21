package com.halilkrkn.chatchef.presentation.ChatGptScreen

import AuthViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.halilkrkn.chatchef.navigation.util.AuthScreen
import com.halilkrkn.chatchef.presentation.components.AiChatMessage
import com.halilkrkn.chatchef.presentation.components.BottomContainer
import com.halilkrkn.chatchef.presentation.components.CustomTopAppBar
import com.halilkrkn.chatchef.presentation.components.UserChatMessage
import com.halilkrkn.chatchef.ui.theme.MainBackgroundColor

@Composable
fun ChatGptScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AuthViewModel = viewModel(),
) {


    Scaffold(
        containerColor = MainBackgroundColor,
        topBar = {
            CustomTopAppBar(
                onBackClick = {
                    viewModel.signOut()
                    navController.navigate(AuthScreen.Login.route)

                },
                notificationClick = { /*TODO*/ }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(innerPadding),
        ) {

            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.weight(1f),
                reverseLayout = true
            ) {
                items(11) { message ->
                    AiChatMessage()
                    Spacer(modifier = Modifier.height(8.dp))
                    UserChatMessage(text = "Helllooooooooo", horizontalAlignment = Alignment.End)
                }
            }
            BottomContainer()
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ChatGptScreenPreview() {
    ChatGptScreen(navController = rememberNavController())
}

