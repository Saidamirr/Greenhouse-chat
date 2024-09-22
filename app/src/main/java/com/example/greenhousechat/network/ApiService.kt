package com.example.greenhousechat.network

import com.example.greenhousechat.data.AuthRequest
import com.example.greenhousechat.data.AuthResponse
import com.example.greenhousechat.data.AvatarDataResponse
import com.example.greenhousechat.data.PhoneRequest
import com.example.greenhousechat.data.ProfileResponse
import com.example.greenhousechat.data.RefreshTokenWrapper
import com.example.greenhousechat.data.RegistrationRequest
import com.example.greenhousechat.data.RegistrationResponse
import com.example.greenhousechat.data.UserProfileChangeApply
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT


val retrofit = Retrofit.Builder()
    .baseUrl("https://plannerok.ru/")  // Базовый URL вашего API
    .addConverterFactory(GsonConverterFactory.create())  // Конвертер для работы с JSON
    .build()

interface ApiService {
    @POST("api/v1/users/send-auth-code/")
    suspend fun getAuthCode(@Body phoneRequest: PhoneRequest): Response<Any>

    @POST("api/v1/users/check-auth-code/")
    suspend fun checkAuthCode(@Body authRequest: AuthRequest): Response<AuthResponse>

    @POST("/api/v1/users/register/")
    suspend fun sendRegistration(@Body registrationRequest: RegistrationRequest): Response<RegistrationResponse>

    @GET("/api/v1/users/me/")
    suspend fun getUserProfile(@Header("Authorization") token: String): Response<ProfileResponse>

    @PUT("/api/v1/users/me/")
    suspend fun putUserProfileChanges(
        @Header("Authorization") token: String,
        @Body putUserChangesRequest: UserProfileChangeApply): Response<AvatarDataResponse>

    @POST("/api/v1/users/refresh-token/")
    suspend fun refreshAccessToken(
        @Body refreshTokenRequest: RefreshTokenWrapper): Response<RegistrationResponse>

}

val apiService = retrofit.create(ApiService::class.java)