package com.halilkrkn.chatchef.presentation.ChatGptScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halilkrkn.chatchef.core.ApiResult.ApiResult
import com.halilkrkn.chatchef.data.remote.dto.Message
import com.halilkrkn.chatchef.data.remote.dto.MessageResponse
import com.halilkrkn.chatchef.data.repository.ChatChefRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatGptViewModel @Inject constructor(private val chatChefRepository: ChatChefRepository) : ViewModel() {

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
            chatChefRepository.sendMessageOpenAi(messageList).collect{ result->
                when (result) {
                    is ApiResult.Loading -> {
                        _chatState.value = chatState.value.copy(isLoading = true)
                    }
                    is ApiResult.Success -> {
                        _chatState.value = chatState.value.copy(messageList = result.data.choices, isLoading = false)
                    }
                    is ApiResult.Error -> {
                        _chatState.value = chatState.value.copy(error = result.message, isLoading = false)
                    }
                }
            }
        }
    }
}