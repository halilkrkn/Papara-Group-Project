package com.halilkrkn.chatchef.data.repository

import com.halilkrkn.chatchef.core.ApiFlow.apiFlow
import com.halilkrkn.chatchef.core.ApiResult.ApiResult
import com.halilkrkn.chatchef.data.remote.ChatChefApi
import com.halilkrkn.chatchef.data.remote.dto.Message
import com.halilkrkn.chatchef.data.remote.dto.OpenAIRequestBody
import com.halilkrkn.chatchef.data.remote.dto.OpenAIResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
class ChatChefRepositoryImpl(
    private val chatChefApi: ChatChefApi
) : ChatChefRepository{

    override fun sendMessageOpenAi(
        list : List<Message>
    ) : Flow<ApiResult<OpenAIResponse>> = apiFlow {
        chatChefApi.generateResponse(OpenAIRequestBody(messages = list))
    }

}