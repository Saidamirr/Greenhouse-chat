package com.example.greenhousechat.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.greenhousechat.data.AuthRequest
import com.example.greenhousechat.data.AuthResponse
import com.example.greenhousechat.data.PhoneRequest
import com.example.greenhousechat.data.RegistrationRequest
import com.example.greenhousechat.data.RegistrationResponse
import com.example.greenhousechat.navigation.Screen
import com.example.greenhousechat.network.apiService
import kotlinx.coroutines.launch
import retrofit2.Response

class AppViewModel(application: Application) : AndroidViewModel(application) {


    var phoneNumber1: String by  mutableStateOf("")
        private set

    var fullPhoneNumber: String by  mutableStateOf("")
        private set

    var isNumberValid: Boolean by  mutableStateOf(false)
        private set

    private val sharedPreferences = getApplication<Application>().getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    private val sharedPreferencesEditor = sharedPreferences.edit()





    fun onNumberInputChange(code: String, phone: String, isValid: Boolean) {
        Log.d("CCP", "onValueChange: $code $phone -> $isValid")
        phoneNumber1 = phone
        fullPhoneNumber = code + phone
        isNumberValid = isValid
    }

    fun onNumberSubmit(navController: NavHostController) {
        var response: Response<Any>
        viewModelScope.launch {
            response = apiService.getAuthCode(PhoneRequest(fullPhoneNumber))
            Log.d("API", "${response.code()}")
            Log.d("API", fullPhoneNumber)
        }
        navController.navigate(Screen.EnterCodeScreen.route)
    }

    var verificationCode: String by  mutableStateOf("")
        private set

    var isVerificationCodeValid: Boolean by  mutableStateOf(false)
        private set

    fun onCodeInputChange(input: String) {
        verificationCode = input
        if( isValidVerificationCode(verificationCode)) {
            isVerificationCodeValid = true
        }
    }

    fun isValidVerificationCode(verificationCode: String): Boolean {
        // Регулярное выражение для проверки строки из 6 цифр
        val regex = Regex("^\\d{6}$")
        return regex.matches(verificationCode)
    }


    fun onCodeSubmit(navController: NavHostController) {
        viewModelScope.launch {
            try {
                val response : Response<AuthResponse> =
                    apiService.checkAuthCode(AuthRequest(phone = fullPhoneNumber, code = verificationCode))

                if (response.isSuccessful) {
                    val authResponse = response.body() // Получаем тело ответа
                    authResponse?.let {
                        // Используем данные из AuthResponse
                        val accessToken = it.accessToken
                        val refreshToken = it.refreshToken
                        val userId = it.userId
                        val isUserExists = it.isUserExists

                        // Дальнейшая логика, например, навигация на следующий экран
                        if (isUserExists) {
                            sharedPreferencesEditor
                                .putString("access_token", accessToken)
                                .putString("refresh_token", refreshToken)
                                .putString("user_id", userId)
                                .apply()
                        } else {
                            navController.navigate(Screen.RegistrationScreen.route)
                        }
                    }
                } else {
                    // Обработка ошибки
                    Log.e("API Error", "Ошибка: ${response.code()}")
                }
            } catch (e: Exception) {
                // Обработка исключений (например, ошибка сети)
                Log.e("Network Error", "Ошибка сети: ${e.message}")
            }
        }
    }

    var name: String by  mutableStateOf("")
        private set

    var userName: String by  mutableStateOf("")
        private set

    var isUsernNameValid: Boolean by  mutableStateOf(false)
        private set

    fun onRegistrationSubmit(navController: NavHostController) {
        viewModelScope.launch {
            try {
                val response : Response<RegistrationResponse> =
                    apiService.sendRegistration(RegistrationRequest(
                        phone = fullPhoneNumber,
                        name = name,
                        userName = userName)
                    )

                if( response.isSuccessful) {
                    val authResponse = response.body() // Получаем тело ответа
                    authResponse?.let {
                        // Используем данные из AuthResponse
                        val accessToken = it.accessToken
                        val refreshToken = it.refreshToken
                        val userId = it.userId


                        // Дальнейшая логика, навигация на следующий экран

                        sharedPreferencesEditor
                            .putString("access_token", accessToken)
                            .putString("refresh_token", refreshToken)
                            .putString("user_id", userId)
                            .apply()

                    }
                }

            } catch (e: Exception) {
                Log.e("Network Error", "Ошибка сети: ${e.message}")
            }
        }
    }

    fun isValidUsername(username: String): Boolean {
        // Регулярное выражение для проверки допустимых символов
        val regex = Regex("^[A-Za-z0-9_-]+$")
        // Проверка, что строка не пустая и длина не превышает 32 символов
        return username.isNotEmpty() && username.length <= 32 && regex.matches(username)
    }



    fun onNameInputChange(it: String) {
        name = it
    }

    fun onUserNameInputChange(it: String) {
        userName = it
        isUsernNameValid = isValidUsername(userName)
    }
}

