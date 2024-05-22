package com.halilkrkn.chatchef.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.halilkrkn.chatchef.core.ApiResult.ApiResult
import com.halilkrkn.chatchef.data.local.model.ChatChefEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatChefDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChatMessage(chatChefEntity: ChatChefEntity)

    @Delete
    suspend fun deleteChatMessage(chatChefEntity: ChatChefEntity)

//    @Query("SELECT * FROM chat_message WHERE userId = :userId")
    @Query("SELECT * FROM chat_message WHERE isFavorite = 1 AND userId = :userId")
    fun getAllFavorite(userId: String): Flow<List<ChatChefEntity>>
}