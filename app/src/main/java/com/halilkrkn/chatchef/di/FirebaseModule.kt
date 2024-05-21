package com.halilkrkn.chatchef.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.halilkrkn.chatchef.data.firebase.implementation.AuthServiceImpl
import com.halilkrkn.chatchef.data.firebase.implementation.FirestoreServiceImpl
import com.halilkrkn.chatchef.data.firebase.service.AuthService
import com.halilkrkn.chatchef.data.firebase.service.FirestoreService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {


}