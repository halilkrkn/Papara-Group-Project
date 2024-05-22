package com.halilkrkn.chatchef.presentation.loginScreen.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halilkrkn.chatchef.data.firebase.FirebaseResult
import com.halilkrkn.chatchef.data.firebase.service.AuthService
import com.halilkrkn.chatchef.data.firebase.service.FirestoreService
import com.halilkrkn.chatchef.presentation.loginScreen.state.AuthUiState
import com.halilkrkn.chatchef.presentation.loginScreen.state.LoggingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthService,
    private val firestoreService: FirestoreService
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    private val _loggingState = MutableStateFlow(LoggingState())
    val loggingState: StateFlow<LoggingState> = _loggingState

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            authRepository.signInWithEmailAndPassword(email, password).collect { result ->
                when (result) {
                    is FirebaseResult.Success -> {
                        result.data?.let { user ->
                            _uiState.value = _uiState.value.copy(user = user, isLoading = false)
                            loadUserData(user.uid)
                        }
                    }

                    is FirebaseResult.Error -> {
                        _uiState.value =
                            _uiState.value.copy(error = result.message, isLoading = false)
                    }

                    FirebaseResult.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun signUp(email: String, password: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            authRepository.signUpWithEmailAndPassword(email, password, firstName, lastName)
                .collect { result ->
                    when (result) {
                        is FirebaseResult.Success -> {
                            result.data?.let { user ->
                                _uiState.value = _uiState.value.copy(
                                    user = user,
                                    firstName = firstName,
                                    lastName = lastName,
                                    isLoading = false
                                )
                            }
                        }

                        is FirebaseResult.Error -> {
                            _uiState.value =
                                _uiState.value.copy(error = result.message, isLoading = false)
                        }

                        FirebaseResult.Loading -> {
                            _uiState.value = _uiState.value.copy(isLoading = true)
                        }
                    }
                }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            authRepository.signOut().collect { result ->
                when (result) {
                    is FirebaseResult.Success -> {
                        _uiState.value = _uiState.value.copy(
                            user = null,
                            firstName = null,
                            lastName = null,
                            isLoading = false
                        )
                    }

                    is FirebaseResult.Error -> {
                        _uiState.value =
                            _uiState.value.copy(error = result.message, isLoading = false)
                    }

                    FirebaseResult.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    private fun loadUserData(userId: String) {
        viewModelScope.launch {
            val userData = firestoreService.getUserData(userId)
            _uiState.value = if (userData != null) {
                _uiState.value.copy(
                    firstName = userData["firstName"],
                    lastName = userData["lastName"],
                    isLoading = false
                )
            } else {
                _uiState.value.copy(error = "Failed to load user data", isLoading = false)
            }
        }
    }


    fun isLoggedIn() {
        viewModelScope.launch {
            authRepository.isLoggedIn().collect { isLoggedIn ->
                _loggingState.value = _loggingState.value.copy(isLoading = true)
                when (isLoggedIn) {
                    is FirebaseResult.Success -> {
                        Log.d("com.halilkrkn.chatchef.presentation.loginScreen.viewModel.AuthViewModel", "isLoggedIn: ${isLoggedIn.data}")
                        _loggingState.value = _loggingState.value.copy(
                            isLoading = false,
                            transaction = isLoggedIn.data ?: false
                        )
                    }
                    is FirebaseResult.Error -> {
                        Log.e("com.halilkrkn.chatchef.presentation.loginScreen.viewModel.AuthViewModel", "isLoggedIn error: ${isLoggedIn.message}")
                        _loggingState.value = _loggingState.value.copy(
                            isLoading = false,
                            error = isLoggedIn.message
                        )
                    }
                    FirebaseResult.Loading -> {
                        Log.d("com.halilkrkn.chatchef.presentation.loginScreen.viewModel.AuthViewModel", "isLoggedIn loading")
                        _loggingState.value = _loggingState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }
}
