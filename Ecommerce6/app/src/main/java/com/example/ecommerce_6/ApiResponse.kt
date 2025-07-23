package com.example.ecommerce_6

// Generic API response structure
data class ApiResponse<T>(
    val message: String,
    val user: User? = null, // Can be null for login/logout messages
    val token: String? = null, // Can be null for logout
    val errors: Map<String, List<String>>? = null // For validation errors
)
