package com.example.greenhousechat.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.greenhousechat.data.ProfileData
import com.example.greenhousechat.viewmodel.AppViewModel

@Composable
fun ProfileScreen(modifier: Modifier = Modifier,
                  appViewModel: AppViewModel,
                  navController: NavHostController
) {
    val profileData: ProfileData = appViewModel.getLocalProfileData()
    ProfileScreenContent(profileData = profileData)

}

@Composable
fun ProfileScreenContent(profileData: ProfileData) {
    Column {
        Text(text = profileData.phone)
        Text(text = profileData.username)
        Text(text = profileData.name)
        Text(text = profileData.birthday)
        Text(text = profileData.status)
    }
}