package com.halilkrkn.chatchef.presentation.loginScreen.state

data class LoggingState(
    val transaction: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)