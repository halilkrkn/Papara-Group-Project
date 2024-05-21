package com.halilkrkn.chatchef.data.firebase.service

import com.google.firebase.auth.FirebaseUser
import com.halilkrkn.chatchef.data.firebase.FirebaseResult
import kotlinx.coroutines.flow.Flow

interface AuthService {
    suspend fun signInWithEmailAndPassword(email: String, password: String): Flow<FirebaseResult<FirebaseUser>>
    suspend fun signUpWithEmailAndPassword(email: String, password: String, firstName: String, lastName: String): Flow<FirebaseResult<FirebaseUser>>
    fun signOut(): Flow<FirebaseResult<Boolean>>
    fun isLoggedIn(): Boolean
    fun sendPasswordResetEmail(email: String): Flow<FirebaseResult<Boolean>>
}