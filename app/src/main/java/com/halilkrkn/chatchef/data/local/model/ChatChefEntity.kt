package com.halilkrkn.chatchef.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_message")
data class ChatChefEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
//    val userId: String,
    val role: String?,
    val content:String?,
    val timestamp: Long?
)