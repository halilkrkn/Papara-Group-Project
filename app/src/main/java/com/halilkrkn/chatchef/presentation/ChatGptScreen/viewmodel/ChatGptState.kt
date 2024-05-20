package com.halilkrkn.chatchef.presentation.ChatGptScreen.viewmodel

import com.halilkrkn.chatchef.data.remote.dto.Message
import com.halilkrkn.chatchef.data.remote.dto.MessageResponse

data class ChatGptState (
    var isLoading : Boolean = false,
    var error : String = "",
    var success : List<MessageResponse> = listOf()
)