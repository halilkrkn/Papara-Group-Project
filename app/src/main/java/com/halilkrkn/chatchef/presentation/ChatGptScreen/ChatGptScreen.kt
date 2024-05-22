package com.halilkrkn.chatchef.presentation.ChatGptScreen

import com.halilkrkn.chatchef.presentation.loginScreen.viewModel.AuthViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.halilkrkn.chatchef.navigation.util.AuthScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.halilkrkn.chatchef.presentation.ChatGptScreen.viewmodel.ChatGptViewModel
import com.halilkrkn.chatchef.presentation.components.AIChatMessage
import com.halilkrkn.chatchef.presentation.components.BottomContainer
import com.halilkrkn.chatchef.presentation.components.CustomTopAppBar
import com.halilkrkn.chatchef.presentation.components.UserChatMessage
import com.halilkrkn.chatchef.ui.theme.MainBackgroundColor


@Composable
fun ChatGptScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ChatGptViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    val chatState by viewModel.chatState.collectAsState()

    Scaffold(
        containerColor = MainBackgroundColor,
        topBar = {
            CustomTopAppBar(
                onBackClick = {
                    navController.popBackStack()
                    authViewModel.signOut()
                    navController.navigate(AuthScreen.Login.route)

                },
                notificationClick = { /*TODO*/ }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = innerPadding.calculateTopPadding()),
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            if (chatState.error.isNotBlank()) {
                Text(text = chatState.error)
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                reverseLayout = true
            ) {
                items(chatState.messageList.reversed()){ messageResponse ->
                    if (messageResponse.message.isUser){
                        UserChatMessage(text = messageResponse.message.content, horizontalAlignment = Alignment.End)
                    }else{
                        AIChatMessage(
                            message = messageResponse.message
                        )
                    }
                }
            }
            BottomContainer(){
                viewModel.sendMessage(it)

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ChatGptScreenPreview() {
    ChatGptScreen(navController = rememberNavController())
}

