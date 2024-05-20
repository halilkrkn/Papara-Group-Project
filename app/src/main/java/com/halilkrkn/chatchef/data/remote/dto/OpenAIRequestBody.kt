package com.halilkrkn.chatchef.data.remote.dto

data class OpenAIRequestBody(
    val model: String = "gpt-3.5-turbo",
    val messages: List<Message>,
    val max_tokens: Int = 1000,
    val n: Int = 1,
    val temperature: Double = 1.0
)
