package com.halilkrkn.chatchef.presentation.loginScreen.state

import com.google.firebase.auth.FirebaseUser

data class AuthUiState(
    val isLoading: Boolean = false,
    val user: FirebaseUser? = null,
    val error: String? = null,
    val firstName: String? = null,
    val lastName: String? = null
)

