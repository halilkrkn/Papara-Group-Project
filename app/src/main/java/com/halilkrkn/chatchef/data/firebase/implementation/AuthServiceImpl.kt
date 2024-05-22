package com.halilkrkn.chatchef.data.firebase.implementation

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.getInstance
import com.google.firebase.auth.FirebaseUser
import com.halilkrkn.chatchef.data.firebase.FirebaseResult
import com.halilkrkn.chatchef.data.firebase.service.AuthService
import com.halilkrkn.chatchef.data.firebase.service.FirestoreService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthServiceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestoreService: FirestoreService
) : AuthService {

    private val auth = getInstance()

    override suspend fun signInWithEmailAndPassword(email: String, password: String): Flow<FirebaseResult<FirebaseUser>> = flow {
        emit(FirebaseResult.Loading)
        try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(FirebaseResult.Success(result.user))
        } catch (e: Exception) {
            emit(FirebaseResult.Error(e.message ?: "Something went wrong"))
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun signUpWithEmailAndPassword(email: String, password: String, firstName: String, lastName: String): Flow<FirebaseResult<FirebaseUser>> = flow {
        emit(FirebaseResult.Loading)
        try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user
            user?.let {
                firestoreService.saveUserData(it.uid, firstName, lastName)
            }
            emit(FirebaseResult.Success(user))
        } catch (e: Exception) {
            emit(FirebaseResult.Error(e.message ?: "Something went wrong"))
        }
    }.flowOn(Dispatchers.IO)


    override fun signOut(): Flow<FirebaseResult<Boolean>> = flow {
        emit(FirebaseResult.Loading)
        try {
            firebaseAuth.signOut()
            emit(FirebaseResult.Success(true))
        } catch (e: Exception) {
            emit(FirebaseResult.Error(e.message ?: "Something went wrong"))
        }
    }.flowOn(Dispatchers.IO)


    override fun isLoggedIn(): Flow<FirebaseResult<Boolean>> = flow {
        emit(FirebaseResult.Loading)
        try {
            val user = firebaseAuth.currentUser
            if (user != null) {
                emit(FirebaseResult.Success(true))}
            else {
                emit(FirebaseResult.Success(false))
            }
        }
        catch (e:Exception){
            emit(FirebaseResult.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun sendPasswordResetEmail(email: String): Flow<FirebaseResult<Boolean>> = flow {
        emit(FirebaseResult.Loading)
        try {

            auth.sendPasswordResetEmail(email).await()
            emit(FirebaseResult.Success(true))
        }
        catch (e: Exception){
            emit(FirebaseResult.Error(e.message ?: "Something went wrong"))
        }
    }
}
