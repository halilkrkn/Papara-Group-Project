package com.halilkrkn.chatchef.data.remote.dto

data class Message(val content: String, val role: String) {
    val isUser: Boolean
        get() = role == "user"
}