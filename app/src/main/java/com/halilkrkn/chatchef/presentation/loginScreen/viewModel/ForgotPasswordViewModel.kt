package com.halilkrkn.chatchef.presentation.loginScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.halilkrkn.chatchef.data.firebase.FirebaseResult
import com.halilkrkn.chatchef.data.firebase.implementation.AuthServiceImpl
import com.halilkrkn.chatchef.data.firebase.service.AuthService
import com.halilkrkn.chatchef.presentation.loginScreen.state.LoggingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authRepository: AuthService
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoggingState())
    val uiState: StateFlow<LoggingState> = _uiState

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
                        _uiState.value = _uiState.value.copy(transaction = true, isLoading = false)
                    }
                    is FirebaseResult.Error -> {
                        _uiState.value = _uiState.value.copy(error = result.message, isLoading = false, transaction = false)
                    }
                }
            }
        }
    }
}