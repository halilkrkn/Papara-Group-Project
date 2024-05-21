package com.halilkrkn.chatchef.data.repository

import com.halilkrkn.chatchef.core.ApiFlow.apiFlow
import com.halilkrkn.chatchef.core.ApiResult.ApiResult
import com.halilkrkn.chatchef.data.local.db.ChatChefDatabase
import com.halilkrkn.chatchef.data.local.model.ChatChefEntity
import com.halilkrkn.chatchef.data.remote.ChatChefApi
import com.halilkrkn.chatchef.data.remote.dto.Message
import com.halilkrkn.chatchef.data.remote.dto.OpenAIRequestBody
import com.halilkrkn.chatchef.data.remote.dto.OpenAIResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class ChatChefRepositoryImpl @Inject constructor(
    private val chatChefApi: ChatChefApi,
    private val chatChefDatabase: ChatChefDatabase
) : ChatChefRepository{

    override fun sendMessageOpenAi(
        list : List<Message>
    ) : Flow<ApiResult<OpenAIResponse>> = apiFlow {
        chatChefApi.generateResponse(OpenAIRequestBody(messages = list))
    }

    override suspend fun insertMessage(message: ChatChefEntity) {
        chatChefDatabase.chatChefDao().insertChatMessage(message)
    }

    override suspend fun deleteMessage(message: ChatChefEntity) {
        chatChefDatabase.chatChefDao().deleteChatMessage(message)
    }
    override fun getAllMessages(/*userId: String*/): Flow<ApiResult<List<ChatChefEntity>>> = flow {
        emit(ApiResult.Loading)
        val messages = chatChefDatabase.chatChefDao().getAllFavorite(/*userId*/).first()
        emit(ApiResult.Success(messages))
    }

}