package com.example.greenhousechat.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.greenhousechat.viewmodel.AppViewModel

@Composable
fun EnterCodeScreen(modifier: Modifier = Modifier,
                    appViewModel: AppViewModel,
                    navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),

            value = appViewModel.verificationCode,
            onValueChange = { appViewModel.onCodeInputChange(it) },
            label = { Text(text = "Enter code")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
        )

        Button(onClick = { appViewModel.onCodeSubmit(navController) },
            enabled = appViewModel.isVerificationCodeValid
        ) {
            androidx.compose.material3.Text("Submit")
        }
    }
}