package com.halilkrkn.chatchef.presentation.ChatGptScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.halilkrkn.chatchef.data.repository.ChatChefRepository
import javax.inject.Inject
import javax.inject.Singleton
/*
@Singleton
class ChatAiViewModelFactory@Inject constructor(
    private val chatChefRepository: ChatChefRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatGptViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChatGptViewModel(chatChefRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/