package com.halilkrkn.chatchef.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.halilkrkn.chatchef.data.local.dao.ChatChefDao
import com.halilkrkn.chatchef.data.local.model.ChatChefEntity

@Database(
    entities = [ChatChefEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ChatChefDatabase : RoomDatabase() {
    abstract fun chatChefDao(): ChatChefDao
}
