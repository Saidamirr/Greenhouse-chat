package com.example.greenhousechat.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.greenhousechat.ui.theme.Typography
import com.example.greenhousechat.viewmodel.AppViewModel

@Composable
fun RegistrationScreen(modifier: Modifier = Modifier,
                    appViewModel: AppViewModel,
                    navController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
            value = appViewModel.fullPhoneNumber,
            onValueChange = { },
            label = { Text(text = "Номер") },
            enabled = false)

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
            value = appViewModel.name,
            onValueChange = { appViewModel.onNameInputChange(it) },
            label = { Text(text = "Имя") },
            )

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
            value = appViewModel.userName,
            onValueChange = { appViewModel.onUserNameInputChange(it) },
            label = { Text(text = "Username") },
            )

        Button(onClick = { appViewModel.onRegistrationSubmit(navController) },
            enabled = appViewModel.isUsernNameValid
        ) {
            androidx.compose.material3.Text("Отправить", style = Typography.labelSmall)
        }
    }
}