package com.example.greenhousechat.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.greenhousechat.data.AuthRequest
import com.example.greenhousechat.data.AuthResponse
import com.example.greenhousechat.data.Avatars
import com.example.greenhousechat.data.PhoneRequest
import com.example.greenhousechat.data.ProfileData
import com.example.greenhousechat.data.ProfileResponse
import com.example.greenhousechat.data.RegistrationRequest
import com.example.greenhousechat.data.RegistrationResponse
import com.example.greenhousechat.navigation.Screen
import com.example.greenhousechat.network.apiService
import kotlinx.coroutines.launch
import retrofit2.Response

@SuppressLint("StaticFieldLeak")
class AppViewModel(application: Application) : AndroidViewModel(application) {


    var phoneNumber1: String by  mutableStateOf("")
        private set

    var fullPhoneNumber: String by  mutableStateOf("")
        private set

    var isNumberValid: Boolean by  mutableStateOf(false)
        private set

    private val context = getApplication<Application>().applicationContext

    private val sharedPreferences = getApplication<Application>().getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    private val sharedPreferencesEditor = sharedPreferences.edit()





    fun onNumberInputChange(code: String, phone: String, isValid: Boolean) {
        Log.d("CCP", "onValueChange: $code $phone -> $isValid")
        phoneNumber1 = phone
        fullPhoneNumber = code + phone
        isNumberValid = isValid
    }

    fun onNumberSubmit(navController: NavHostController) {
        viewModelScope.launch {
            try {
                val response =
                    apiService.getAuthCode(PhoneRequest(fullPhoneNumber))
                Toast.makeText(context, "Запрос отправлен, ожидаем код...", Toast.LENGTH_SHORT).show()

                if (response.isSuccessful) {
                    navController.navigate(Screen.EnterCodeScreen.route)
                } else if (response.code() == 422) {
                    Toast.makeText(context, "Неверный номер телефона", Toast.LENGTH_SHORT).show()
                    Log.e("Network Error", "Неверный номер телефона")

                }
            } catch (e: Exception) {
                // Обработка исключений (например, ошибка сети)
                Toast.makeText(context, "Проверьте интернет-соединение...", Toast.LENGTH_SHORT).show()
                Log.e("Network Error", "Ошибка сети: ${e.message}")
            }
        }
    }

    var verificationCode: String by  mutableStateOf("")
        private set

    var isVerificationCodeValid: Boolean by  mutableStateOf(false)
        private set

    fun onCodeInputChange(input: String) {
        if(input.length <= 6) verificationCode = input
        if( isValidVerificationCode(verificationCode)) isVerificationCodeValid = true
        else isVerificationCodeValid = false
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
                Toast.makeText(context, "Запрос отправлен, ожидаем...", Toast.LENGTH_SHORT).show()

                if (response.isSuccessful) {
                    val authResponse = response.body() // Получаем тело ответа
                    authResponse?.let {
                        // Используем данные из AuthResponse
                        val accessToken = it.access_token
                        val refreshToken = it.refresh_token
                        val userId = it.user_id
                        val isUserExists = it.is_user_exists

                        // Дальнейшая логика, например, навигация на следующий экран
                        if (isUserExists) {
                            sharedPreferencesEditor
                                .putString("access_token", accessToken)
                                .putString("refresh_token", refreshToken)
                                .putString("user_id", userId)
                                .putBoolean("is_authorized", true) //вход в акк
                                .apply()
                            getProfileData()
                            navController.navigate(Screen.ChatScreen.route) {
                                popUpTo(0) { inclusive = true } // Удаляем все экраны из стека
                                launchSingleTop = true // Предотвращаем дублирование экрана
                            }
                        } else {
                            navController.navigate(Screen.RegistrationScreen.route)
                        }
                    }
                } else if (response.code() == 422) {
                    Toast.makeText(context, "Неверный код", Toast.LENGTH_SHORT).show()
                    Log.e("Network Error", "Неверный код")

                } else {
                    Toast.makeText(context, "Что-то пошло не так", Toast.LENGTH_SHORT).show()

                }
            } catch (e: Exception) {
                // Обработка исключений (например, ошибка сети)
                Toast.makeText(context, "Проверьте интернет-соединение...", Toast.LENGTH_SHORT).show()
                Log.e("Network Error", "Ошибка сети: ${e.message}")
            }
        }
    }

    fun getIsAuthorized(): Boolean {
        return sharedPreferences.getBoolean("is_authorized", false)
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
                        username = userName)
                    )
                Toast.makeText(context, "Запрос отправлен, ожидаем...", Toast.LENGTH_SHORT).show()

                if( response.isSuccessful) {
                    val authResponse = response.body() // Получаем тело ответа
                    authResponse?.let {
                        // Используем данные из AuthResponse
                        val accessToken = it.access_token
                        val refreshToken = it.refresh_token
                        val userId = it.user_id

                        // Дальнейшая логика, навигация на следующий экран

                        sharedPreferencesEditor
                            .putString("access_token", accessToken)
                            .putString("refresh_token", refreshToken)
                            .putString("user_id", userId)
                            .putBoolean("is_authorized", true) //вход в акк
                            .apply()
                        getProfileData() // кеширование
                    }
                    navController.navigate(Screen.ChatScreen.route) {
                        popUpTo(0) { inclusive = true } // Удаляем все экраны из стека
                        launchSingleTop = true // Предотвращаем дублирование экрана
                    }
                } else if (response.code() == 422) {
                    Toast.makeText(context, "Неверный формат Username", Toast.LENGTH_SHORT).show()
                    Log.e("Network Error", "Ошибка сети:")

                }

            } catch (e: Exception) {
                Log.e("Network Error", "Ошибка сети: ${e.message}")
                Toast.makeText(context, "Проверьте интернет-соединение...", Toast.LENGTH_SHORT).show()

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

    fun onToProfileButtonClick(navController: NavHostController) {
        navController.navigate(Screen.ProfileScreen.route)
    }

    fun getProfileData() {
        viewModelScope.launch {
            try {
                val accessToken = sharedPreferences.getString("access_token", "") ?:  " "
                val tokenString = "Bearer $accessToken"
                val response : Response<ProfileResponse> =
                    apiService.getUserProfile(tokenString)

                if( response.isSuccessful) {
                    val profileData = response.body()?.profile_data // Получаем тело ответа
                    //Сохраняем данные из AuthResponse
                    sharedPreferencesEditor
                        .putString("name", profileData?.name)
                        .putString("name", profileData?.name)
                        .putString("vk", profileData?.vk)
                        .putString("city", profileData?.city)
                        .putString("last", profileData?.last)
                        .putString("avatar", profileData?.avatar)
                        .putString("avatarNormal", profileData?.avatars?.avatar)
                        .putString("avatarBig", profileData?.avatars?.bigAvatar)
                        .putString("avatarMini", profileData?.avatars?.miniAvatar)
                        .putString("birthday", profileData?.birthday)
                        .putString("created", profileData?.created)
                        .putString("phone", profileData?.phone)
                        .putString("instagram", profileData?.instagram)
                        .putString("status", profileData?.status)
                        .putString("username", profileData?.username)
                        .putInt("completed_tasks", profileData?.completed_task ?: 0)
                        .putInt("id", profileData?.id  ?: 0)
                        .putBoolean("isOnline", profileData?.online ?: false)
                        .apply()
                } else {
                    Log.e("Network Error", "Ошибка: Запрос профиля был неуспешен")

                }

            } catch (e: Exception) {
                Log.e("Network Error", "Ошибка сети: ${e.message}")
                Toast.makeText(context, "Проверьте интернет-соединение...", Toast.LENGTH_SHORT).show()

            }
        }
    }

    fun getLocalProfileData(): ProfileData {
        return ProfileData(
            name = sharedPreferences.getString("name", "") ?: "default",
            username = sharedPreferences.getString("username", "") ?: "default",
            birthday = sharedPreferences.getString("birthday", "") ?: "default",
            city = sharedPreferences.getString("city", "") ?: "default",
            avatar = sharedPreferences.getString("avatar", "") ?: "default",
            vk = sharedPreferences.getString("vk", "") ?: "default",
            instagram = sharedPreferences.getString("instagram", "") ?: "default",
            status = sharedPreferences.getString("status", "") ?: "default",
            id = sharedPreferences.getInt("id", 0),
            last = sharedPreferences.getString("last", "") ?: "default",
            online  = sharedPreferences.getBoolean("online", false),
            created = sharedPreferences.getString("created", "") ?: "default",
            phone = sharedPreferences.getString("phone", "") ?: "default",
            completed_task = sharedPreferences.getInt("completed_task", 0),
            avatars = Avatars(
                avatar = sharedPreferences.getString("avatarNormal", "") ?: "default",
                bigAvatar = sharedPreferences.getString("avatarBig", "") ?: "default",
                miniAvatar = sharedPreferences.getString("avatarMini", "") ?: "default"
                )
            )
    }

    fun signOut(navController: NavHostController) {
        sharedPreferencesEditor.clear().apply()
        navController.navigate(Screen.SendPhoneScreen.route)
    }

    fun logPrefs() {
        val accessToken = sharedPreferences.getString("access_token", "...")
        Log.d("SharedPreferences", "access token: $accessToken")
    }

    fun onEditProfile(navController: NavHostController) {
        navController.navigate(Screen.EditProfileScreen.route)
    }
}

