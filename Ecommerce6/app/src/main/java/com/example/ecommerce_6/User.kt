package com.example.ecommerce_6

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    val name: String,
    val email: String,
    @SerializedName("role_id")
    val roleId: Int
    // Add other user fields as needed
)
