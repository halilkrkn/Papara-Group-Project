package com.halilkrkn.chatchef.data.firebase.service

interface FirestoreService {
    suspend fun saveUserData(userId: String, firstName: String, lastName: String)
    suspend fun getUserData(userId: String): Map<String, String>?
}