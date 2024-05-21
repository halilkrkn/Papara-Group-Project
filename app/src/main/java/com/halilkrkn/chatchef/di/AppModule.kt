package com.halilkrkn.chatchef.di

import android.content.Context
import androidx.room.Room
import com.halilkrkn.chatchef.core.interceptor.ApiInterceptor
import com.halilkrkn.chatchef.data.local.db.ChatChefDatabase
import com.halilkrkn.chatchef.data.remote.ChatChefApi
import com.halilkrkn.chatchef.data.repository.ChatChefRepository
import com.halilkrkn.chatchef.data.repository.ChatChefRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ApiInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openai.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenAIApi(retrofit: Retrofit): ChatChefApi {
        return retrofit.create(ChatChefApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOpenAIRepository(
        openAIApi: ChatChefApi,
        chatChefDatabase: ChatChefDatabase
        ): ChatChefRepository {
        return ChatChefRepositoryImpl(
            openAIApi,
            chatChefDatabase
        )
    }
}