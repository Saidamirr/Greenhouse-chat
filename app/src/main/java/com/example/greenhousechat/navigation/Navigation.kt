package com.example.greenhousechat.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.greenhousechat.ui.ChatScreen
import com.example.greenhousechat.ui.EnterCodeScreen
import com.example.greenhousechat.ui.ProfileScreen
import com.example.greenhousechat.ui.RegistrationScreen
import com.example.greenhousechat.ui.SendPhoneScreen
import com.example.greenhousechat.viewmodel.AppViewModel

sealed class Screen(val route: String) {
    object SendPhoneScreen : Screen("send_phone")
    object EnterCodeScreen : Screen("enter_code")
    object RegistrationScreen : Screen("registration")
    object ChatScreen : Screen("chats")
    object ProfileScreen : Screen("profile")
}

@Composable
fun AppNavHost(navController: NavHostController, appViewModel: AppViewModel) {

    NavHost(navController = navController, startDestination = Screen.ChatScreen.route) {
        composable(Screen.SendPhoneScreen.route) {
            SendPhoneScreen(navController = navController, appViewModel = appViewModel)
        }
        composable(Screen.EnterCodeScreen.route) {
            EnterCodeScreen(navController = navController, appViewModel = appViewModel)
        }
        composable(Screen.RegistrationScreen.route) {
            RegistrationScreen(navController = navController, appViewModel = appViewModel)
        }
        composable(Screen.ChatScreen.route) {
            ChatScreen()
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(appViewModel = appViewModel, navController = navController)
        }
    }
}