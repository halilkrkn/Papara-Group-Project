package com.halilkrkn.chatchef.data.repository

import com.halilkrkn.chatchef.core.ApiResult.ApiResult
import com.halilkrkn.chatchef.data.remote.dto.Message
import com.halilkrkn.chatchef.data.remote.dto.OpenAIResponse
import kotlinx.coroutines.flow.Flow

interface ChatChefRepository {

    fun sendMessageOpenAi(
        list : List<Message>
    ) : Flow<ApiResult<OpenAIResponse>>

}