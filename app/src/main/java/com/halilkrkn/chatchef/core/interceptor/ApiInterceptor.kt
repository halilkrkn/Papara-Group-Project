package com.halilkrkn.chatchef.core.interceptor

import com.halilkrkn.chatchef.getApiKey
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val apiKey = getApiKey()
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", apiKey)
            .build()
        return chain.proceed(newRequest)
    }
}
