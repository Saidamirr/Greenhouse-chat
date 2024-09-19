package com.example.greenhousechat.data

data class AuthRequest(
    val phone: String,
    val code: String
)

data class AuthResponse(
    val access_token: String,
    val refresh_token: String,
    val user_id: String,
    val is_user_exists: Boolean
)

data class RegistrationRequest(
    val phone: String,
    val name: String,
    val username: String
)

data class RegistrationResponse(
    val access_token: String,
    val refresh_token: String,
    val user_id: String,
)

data class PhoneRequest(
    val phone: String
)