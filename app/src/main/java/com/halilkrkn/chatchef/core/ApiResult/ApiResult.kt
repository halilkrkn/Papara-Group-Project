package com.halilkrkn.chatchef.core.ApiResult

sealed class ApiResult<out T> {

    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val message: String) : ApiResult<Nothing>()
    data object Loading : ApiResult<Nothing>()

}