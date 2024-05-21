package com.halilkrkn.chatchef.presentation.ChatGptScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.halilkrkn.chatchef.navigation.util.AuthScreen
import com.halilkrkn.chatchef.presentation.ChatGptScreen.viewmodel.ChatGptViewModel
import com.halilkrkn.chatchef.presentation.components.AIChatMessage
import com.halilkrkn.chatchef.presentation.components.BottomContainer
import com.halilkrkn.chatchef.presentation.components.CustomTopAppBar
import com.halilkrkn.chatchef.ui.theme.MainBackgroundColor

@Composable
fun ChatGptScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ChatGptViewModel = hiltViewModel(),
) {
    val chatState by viewModel.chatState.collectAsState()

    Scaffold(
        containerColor = MainBackgroundColor,
        topBar = {
            CustomTopAppBar(
                onBackClick = { navController.navigate(AuthScreen.Login.route) },
                notificationClick = { /*TODO*/ }
            )
        }
    ) { innerPadding ->
        println(chatState)

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = innerPadding.calculateTopPadding()),
        ) {
            Row(
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
            }
            Spacer(modifier = Modifier.height(8.dp))


            if (chatState.error.isNotBlank()) {
                Text(text = chatState.error)
            } else if (chatState.messageList.isNotEmpty()) {

                if (chatState.isLoading) {
                    //CircularProgressIndicator()
                } else if (chatState.error.isNotBlank()) {
                    Text(text = chatState.error)
                } else if (chatState.messageList.isNotEmpty()) {

                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp),
                        reverseLayout = true
                    ) {

                        items(chatState.messageList) { messageResponse ->
                            AIChatMessage(message = messageResponse.message)

                            /*
                    items(chatState.success)) { message ->
                        if (message.isUser) {
                            MessageBubble(text = message.content, horizontalAlignment = Alignment.End)
                        } else {
                            MessageBubble(text = message.content, horizontalAlignment = Alignment.Start)
                        }
                    }*/
                        }
//                        items(chatState.messageList) {messageResponse ->
//                            AIChatMessage(message = messageResponse.message)
//
//                        }
                    }

                    BottomContainer() {
                        viewModel.sendMessage(it)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ChatGptScreenPreview() {
    ChatGptScreen(navController = rememberNavController())
}

