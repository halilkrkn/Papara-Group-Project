package com.halilkrkn.chatchef.presentation.ChatGptScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halilkrkn.chatchef.core.ApiResult.ApiResult
import com.halilkrkn.chatchef.data.local.model.ChatChefEntity
import com.halilkrkn.chatchef.data.mapper.toChatChefEntity
import com.halilkrkn.chatchef.data.remote.dto.Message
import com.halilkrkn.chatchef.data.remote.dto.MessageResponse
import com.halilkrkn.chatchef.data.repository.ChatChefRepository
import com.halilkrkn.chatchef.presentation.FavoriteScreen.FavoriteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatGptViewModel @Inject constructor(
    private val chatChefRepository: ChatChefRepository
) : ViewModel() {

    private val _chatState = MutableStateFlow(ChatGptState())
    val chatState: StateFlow<ChatGptState> = _chatState
    private val list = _chatState.value.messageList.toMutableList()
    private var messageList = list.map {
        it.message
    }.toMutableList()

    fun sendMessage(text: String) {
        val message = Message(content = text, role = "user")
        messageList.add(message)
        list.add(MessageResponse(message = message))
        _chatState.value = chatState.value.copy(messageList = list)
        viewModelScope.launch {
            chatChefRepository.sendMessageOpenAi(messageList).collect { result ->
                when (result) {
                    is ApiResult.Loading -> {
                        _chatState.value = chatState.value.copy(isLoading = true)
                    }

                    is ApiResult.Success -> {
                        _chatState.value = chatState.value.copy(
                            messageList = result.data.choices,
                            isLoading = false
                        )
                    }

                    is ApiResult.Error -> {
                        _chatState.value =
                            chatState.value.copy(error = result.message, isLoading = false)
                    }
                }
            }
        }
    }

    init {
        sendMessage(listOf(Message("Selam", "user")))
    }

    fun sendMessage(messages: List<Message>) {
        viewModelScope.launch {
            chatChefRepository.sendMessageOpenAi(messages).collect {
                when (it) {
                    is ApiResult.Loading -> {
                        _chatState.value = chatState.value.copy(isLoading = true)

                    }

                    is ApiResult.Success -> {
                        _chatState.value =
                            chatState.value.copy(messageList = it.data.choices, isLoading = false)
                    }

                    is ApiResult.Error -> {
                        _chatState.value =
                            chatState.value.copy(error = it.message, isLoading = false)
                    }
                }
            }
        }
    }

    // Database Operations
    fun insertMessage(message: ChatChefEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            chatChefRepository.insertMessage(message)
        }
    }

    fun deleteMessage(message: ChatChefEntity) {
        viewModelScope.launch(Dispatchers.IO){
            chatChefRepository.deleteMessage(message)
        }
    }
}
