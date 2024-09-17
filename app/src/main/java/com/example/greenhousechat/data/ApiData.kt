package com.example.greenhousechat.data

data class AuthRequest(
    val phone: String,
    val code: String
)

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String,
    val userId: String,
    val isUserExists: Boolean
)