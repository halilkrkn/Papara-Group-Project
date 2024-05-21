package com.halilkrkn.chatchef.presentation.loginScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.halilkrkn.chatchef.data.firebase.FirebaseResult
import com.halilkrkn.chatchef.data.firebase.implementation.AuthServiceImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch

data class AuthUiState(
    val transactionState: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
)

class ForgotPasswordViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState
    private val authRepository: AuthServiceImpl = AuthServiceImpl()

    fun sendPasswordResetEmail(email: String) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            authRepository.sendPasswordResetEmail(email).collect{
                result ->
                when(result){
                    is FirebaseResult.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                    is FirebaseResult.Success -> {
                        _uiState.value = _uiState.value.copy(transactionState = true, isLoading = false)
                    }
                    is FirebaseResult.Error -> {
                        _uiState.value = _uiState.value.copy(error = result.message, isLoading = false, transactionState = false)
                    }
                }
            }
        }
    }

}