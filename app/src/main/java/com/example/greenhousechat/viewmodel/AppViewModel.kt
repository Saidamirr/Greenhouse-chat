package com.example.greenhousechat.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.greenhousechat.data.AuthRequest
import com.example.greenhousechat.data.AuthResponse
import com.example.greenhousechat.navigation.Screen
import com.example.greenhousechat.network.apiService
import com.togitech.ccp.data.PhoneCode
import kotlinx.coroutines.launch
import retrofit2.Response

class AppViewModel : ViewModel() {

    var phoneNumber1: String by  mutableStateOf("")
        private set
    var fullPhoneNumber: String by  mutableStateOf("")
        private set

    var isNumberValid: Boolean by  mutableStateOf(false)
        private set

    var verificationCode: String by  mutableStateOf("")
        private set

    fun onNumberInputChange(code: String, phone: String, isValid: Boolean) {
        Log.d("CCP", "onValueChange: $code $phone -> $isValid")
        phoneNumber1 = phone
        fullPhoneNumber = code + phone
        isNumberValid = isValid
    }

    fun onNumberSubmit(navController: NavHostController) {
        var response: Response<Any>
        viewModelScope.launch {
            response = apiService.getAuthCode(fullPhoneNumber)
            Log.d("API", "${response.code()}")
            Log.d("API", fullPhoneNumber)
        }
        navController.navigate(Screen.EnterCodeScreen.route)
    }

    fun onCodeInputChange(input: String) {
        verificationCode = input
    }

    fun onCodeSubmit(navController: NavHostController) {
        var response1: Response<AuthResponse>
        viewModelScope.launch {
            response1 = apiService.checkAuthCode(AuthRequest(phone = fullPhoneNumber, code = verificationCode))
        }
    }
}

