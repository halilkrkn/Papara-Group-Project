package com.halilkrkn.chatchef.data.firebase.implementation

import com.google.firebase.firestore.FirebaseFirestore
import com.halilkrkn.chatchef.data.firebase.service.FirestoreService
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreServiceImpl : FirestoreService {

    private val firestore: FirebaseFirestore
        get() = FirebaseFirestore.getInstance()

    override suspend fun saveUserData(userId: String, firstName: String, lastName: String) {
        val userMap = mapOf(
            "firstName" to firstName,
            "lastName" to lastName
        )
        firestore.collection("users").document(userId).set(userMap).await()
    }

    override suspend fun getUserData(userId: String): Map<String, String>? {
        return try {
            val document = firestore.collection("users").document(userId).get().await()
            if (document.exists()) {
                document.data as? Map<String, String>
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}