package com.halilkrkn.chatchef.data.mapper

import com.google.firebase.auth.FirebaseAuth
import com.halilkrkn.chatchef.data.local.model.ChatChefEntity
import com.halilkrkn.chatchef.data.remote.dto.Message

fun Message.toChatChefEntity() = ChatChefEntity(
    userId = FirebaseAuth.getInstance().currentUser?.uid.toString(),
    content = content,
    role = role,
    isFavorite = false,
    timestamp = System.currentTimeMillis(),
)