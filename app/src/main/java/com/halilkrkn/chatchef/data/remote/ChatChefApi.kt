package com.halilkrkn.chatchef.data.remote

import com.halilkrkn.chatchef.data.remote.dto.OpenAIRequestBody
import com.halilkrkn.chatchef.data.remote.dto.OpenAIResponse
import retrofit2.http.Body
import retrofit2.http.POST
interface ChatChefApi {
    @POST("v1/chat/completions")
    suspend fun generateResponse(@Body requestBody: OpenAIRequestBody): OpenAIResponse

}