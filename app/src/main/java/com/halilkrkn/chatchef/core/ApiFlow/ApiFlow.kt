package com.halilkrkn.chatchef.core.ApiFlow

import coil.network.HttpException
import com.halilkrkn.chatchef.core.ApiResult.ApiResult
import com.halilkrkn.chatchef.data.remote.dto.OpenAIResponse
import kotlinx.coroutines.flow.flow

fun apiFlow(
    call : suspend () -> OpenAIResponse
) = flow<ApiResult<OpenAIResponse>> {

    emit(ApiResult.Loading)
    try {
        val response = call()
        emit(ApiResult.Success(response))
    } catch (e: HttpException) {
        emit(ApiResult.Error(e.message ?: "An unexpected error occurred"))
    }

}


