package com.example.ecommerce_6

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val authToken: String?) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val builder = originalRequest.newBuilder()

        // If a token exists, add it to the Authorization header
        authToken?.let {
            builder.header("Authorization", "Bearer $it")
        }

        val newRequest = builder.build()
        return chain.proceed(newRequest)
    }
}