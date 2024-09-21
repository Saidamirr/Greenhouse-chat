package com.example.greenhousechat.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.greenhousechat.ui.ChatScreen
import com.example.greenhousechat.ui.EditProfileScreen
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
    object EditProfileScreen : Screen("edit_profile")
}

@Composable
fun AppNavHost(navController: NavHostController, appViewModel: AppViewModel, firstScreen: String) {

    NavHost(navController = navController, startDestination = firstScreen) {
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
            ChatScreen(navController = navController, appViewModel = appViewModel)
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(appViewModel = appViewModel, navController = navController)
        }
        composable(Screen.EditProfileScreen.route) {
            EditProfileScreen(appViewModel = appViewModel, navController = navController)
        }
    }
}