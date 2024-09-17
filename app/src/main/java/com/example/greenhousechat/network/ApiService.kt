package com.example.greenhousechat.network

import com.example.greenhousechat.data.AuthRequest
import com.example.greenhousechat.data.AuthResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST



val retrofit = Retrofit.Builder()
    .baseUrl("https://plannerok.ru/")  // Базовый URL вашего API
    .addConverterFactory(GsonConverterFactory.create())  // Конвертер для работы с JSON
    .build()

interface ApiService {
    @POST("api/v1/users/send-auth-code/")
    suspend fun getAuthCode(@Body phone: String): Response<Any>

    @POST("api/v1/users/check-auth-code/")
    suspend fun checkAuthCode(@Body authRequest: AuthRequest): Response<AuthResponse>
}

val apiService = retrofit.create(ApiService::class.java)