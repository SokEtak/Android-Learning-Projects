package com.example.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {
    @GET("posts") //endpoint
    fun getAllPosts() : Call<List<Posts>>
}