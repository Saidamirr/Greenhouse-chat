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

data class ProfileResponse(
    val profile_data: ProfileData
)

data class ProfileData(
    val name: String,
    val username: String,
    val birthday: String,
    val city: String,
    val vk: String,
    val instagram: String,
    val status: String,
    val avatar: String,
    val id: Int,
    val last: String,
    val online: Boolean,
    val created: String,
    val phone: String,
    val completed_task: Int,
    val avatars: Avatars
)

data class Avatars(
    val avatar: String,
    val bigAvatar: String,
    val miniAvatar: String
)
