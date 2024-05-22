package com.halilkrkn.chatchef.presentation.ChatGptScreen

import androidx.compose.foundation.layout.Arrangement
import com.halilkrkn.chatchef.presentation.loginScreen.viewModel.AuthViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.halilkrkn.chatchef.navigation.util.AuthScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.halilkrkn.chatchef.R
import com.halilkrkn.chatchef.presentation.ChatGptScreen.utils.suggestions
import com.halilkrkn.chatchef.presentation.ChatGptScreen.viewmodel.ChatGptViewModel
import com.halilkrkn.chatchef.presentation.components.AIChatMessage
import com.halilkrkn.chatchef.presentation.components.BottomContainer
import com.halilkrkn.chatchef.presentation.components.CustomCard
import com.halilkrkn.chatchef.presentation.components.CustomTopAppBar
import com.halilkrkn.chatchef.presentation.components.LoadingComponents
import com.halilkrkn.chatchef.presentation.components.UserChatMessage
import com.halilkrkn.chatchef.ui.theme.MainBackgroundColor
import kotlinx.coroutines.tasks.await


@Composable
fun ChatGptScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ChatGptViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    val chatState by viewModel.chatState.collectAsState()
    val name = remember { mutableStateOf("") }
    val randomSuggestions = remember { suggestions.shuffled().take(5) }

    LaunchedEffect(key1 = true) {
        val userData = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().uid.toString()).get().await()
        name.value = userData.get("firstName").toString()
    }

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
                items(chatState.messageList.reversed()) { messageItem ->
                    if (messageItem.message.isUser) {
                        UserChatMessage(text = messageItem.message.content, horizontalAlignment = Alignment.End)
                    } else {
                        AIChatMessage(
                            message = messageItem.message
                        )
                    }
                }
            }
            if (chatState.messageList.isEmpty()) {
                Text(text = "Selam ${name.value}")
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    items(randomSuggestions) { suggestion ->
                        CustomCard(
                            suggestion = suggestion,
                            onClick = { viewModel.sendMessage(suggestion) },
                            modifier = Modifier.weight(1f),
                            textColor = Color.DarkGray,
                            containerColor = colorResource(id = R.color.top_app_bar_icon_bg)
                        )
                    }
                }
            }
            if(chatState.isLoading){
                LoadingComponents(modifier = Modifier.padding(12.dp))
            }
            BottomContainer { message ->
                viewModel.sendMessage(message)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ChatGptScreenPreview() {
    ChatGptScreen(navController = rememberNavController())
}

