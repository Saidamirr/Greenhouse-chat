package com.example.greenhousechat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.greenhousechat.navigation.AppNavHost
import com.example.greenhousechat.ui.SendPhoneScreen
import com.example.greenhousechat.ui.theme.GreenhouseChatTheme
import com.example.greenhousechat.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreenhouseChatTheme {
                val navController = rememberNavController()
                val appViewModel: AppViewModel = viewModel()
                AppNavHost(navController = navController, appViewModel) // Здесь вызывается NavHost
            }
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}