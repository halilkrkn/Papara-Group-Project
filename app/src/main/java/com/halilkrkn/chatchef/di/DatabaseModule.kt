package com.halilkrkn.chatchef.di

import android.content.Context
import androidx.room.Room
import com.halilkrkn.chatchef.data.local.db.ChatChefDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideChatChefDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ChatChefDatabase::class.java,
        "chat_chef_database"
    )
        .fallbackToDestructiveMigration()
        .build()

}