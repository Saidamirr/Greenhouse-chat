package com.example.greenhousechat.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.greenhousechat.ui.EnterCodeScreen
import com.example.greenhousechat.ui.SendPhoneScreen
import com.example.greenhousechat.viewmodel.AppViewModel

sealed class Screen(val route: String) {
    object SendPhoneScreen : Screen("send_phone")
    object EnterCodeScreen : Screen("enter_code")
}

@Composable
fun AppNavHost(navController: NavHostController) {
    val appViewModel: AppViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.SendPhoneScreen.route) {
        composable(Screen.SendPhoneScreen.route) {
            SendPhoneScreen(navController = navController, appViewModel = appViewModel)
        }
        composable(Screen.EnterCodeScreen.route) {
            EnterCodeScreen(navController = navController, appViewModel = appViewModel)
        }
    }
}