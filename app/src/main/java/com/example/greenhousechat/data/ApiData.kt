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

data class RegistrationRequest(
    val phone: String,
    val name: String,
    val userName: String
)

data class RegistrationResponse(
    val accessToken: String,
    val refreshToken: String,
    val userId: String,
)

data class PhoneRequest(
    val phone: String
)