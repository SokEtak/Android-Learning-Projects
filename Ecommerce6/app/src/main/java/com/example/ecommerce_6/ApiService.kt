package com.example.ecommerce_6

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    fun register(@Body request: RegisterRequest): Call<ApiResponse<Any>>

    @POST("login")
    fun login(@Body request: LoginRequest): Call<ApiResponse<Any>>

    @POST("logout")
    fun logout(): Call<ApiResponse<Any>>

    @POST("logout-all")
    fun logoutAll(): Call<ApiResponse<Any>>
}