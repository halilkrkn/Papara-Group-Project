package com.halilkrkn.chatchef.presentation.FavoriteScreen

import com.halilkrkn.chatchef.data.local.model.ChatChefEntity
import com.halilkrkn.chatchef.data.remote.dto.Message

data class FavoriteState(
    var isLoading: Boolean = false,
    var error: String = "",
    var favoriteList: List<ChatChefEntity> = emptyList()
)
