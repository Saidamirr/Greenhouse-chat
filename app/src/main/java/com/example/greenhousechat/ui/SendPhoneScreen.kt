package com.example.greenhousechat.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.greenhousechat.viewmodel.AppViewModel
import com.togitech.ccp.component.TogiCountryCodePicker
import com.togitech.ccp.data.PhoneCode


@Composable
fun SendPhoneScreen(appViewModel: AppViewModel, navController: NavHostController) {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TogiCountryCodePicker(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            onValueChange = { (code, phone), isValid ->
                appViewModel.onNumberInputChange(code, phone, isValid)
            },
            label = { Text("Phone Number") },
            initialCountryIsoCode = "RU"
        )

        Button(onClick = { appViewModel.onNumberSubmit(navController) },
            enabled = appViewModel.isNumberValid
            ) {
            Text("Submit")
        }
    }
}

