package com.halilkrkn.chatchef.data.firebase

sealed class FirebaseResult <out T> {
    data class Success<out R>(val data: R?) : FirebaseResult<R>()
    data class Error(val message:String) : FirebaseResult<Nothing>()
    data object Loading : FirebaseResult<Nothing>()
}