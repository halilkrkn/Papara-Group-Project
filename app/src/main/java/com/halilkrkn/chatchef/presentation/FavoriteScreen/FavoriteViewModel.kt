package com.halilkrkn.chatchef.presentation.FavoriteScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.halilkrkn.chatchef.core.ApiResult.ApiResult
import com.halilkrkn.chatchef.data.local.model.ChatChefEntity
import com.halilkrkn.chatchef.data.repository.ChatChefRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: ChatChefRepository,
    firebaseUser: FirebaseAuth?,
) : ViewModel() {

    private val userId = firebaseUser?.currentUser?.uid.toString()

    private val _state = mutableStateOf<FavoriteState>(FavoriteState())
    val state: State<FavoriteState> = _state

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


    init {
        getAllFavoriteMessage(userId)
    }

    private fun getAllFavoriteMessage(userId: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllMessages(userId = userId).onEach { result ->
                when (result) {
                    is ApiResult.Success -> {
                        _state.value = FavoriteState(
                            isLoading = false,
                            favoriteList = result.data
                        )
                    }

                    is ApiResult.Error -> {
                        _state.value = FavoriteState(
                            isLoading = false,
                            error = result.message
                        )
                    }

                    is ApiResult.Loading -> {
                        _state.value = FavoriteState(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }

    fun deleteFavoriteMessage(movie: ChatChefEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMessage(movie)
        }
    }

    fun insertFavoriteMessage(movie: ChatChefEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMessage(movie)
        }
    }
}