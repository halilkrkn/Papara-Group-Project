package com.halilkrkn.chatchef.data.repository

import com.halilkrkn.chatchef.core.ApiResult.ApiResult
import com.halilkrkn.chatchef.data.local.model.ChatChefEntity
import com.halilkrkn.chatchef.data.remote.dto.Message
import com.halilkrkn.chatchef.data.remote.dto.OpenAIResponse
import kotlinx.coroutines.flow.Flow

interface ChatChefRepository {

    fun sendMessageOpenAi(
        list: List<Message>,
    ): Flow<ApiResult<OpenAIResponse>>

    // Database Operations
    suspend fun insertMessage(message: ChatChefEntity)
    suspend fun deleteMessage(message: ChatChefEntity)
    fun getAllMessages(userId: String): Flow<ApiResult<List<ChatChefEntity>>>

}