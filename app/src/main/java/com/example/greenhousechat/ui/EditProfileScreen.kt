package com.example.greenhousechat.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.greenhousechat.R
import com.example.greenhousechat.data.ProfileData
import com.example.greenhousechat.ui.theme.Typography
import com.example.greenhousechat.viewmodel.AppViewModel

@Composable
fun EditProfileScreen(modifier: Modifier = Modifier,
                  appViewModel: AppViewModel,
                  navController: NavHostController
) {
    val profileData: ProfileData = appViewModel.getLocalProfileData()

    Column(modifier = Modifier
        .background(color = Color(236, 250, 235))
        .padding(vertical = 30.dp, horizontal = 15.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        EditProfileScreenContent(profileData = profileData)

        Column {
            Button(onClick = { appViewModel.onEditProfile(navController)}) {
                Text(text = "Сохранить", style = Typography.labelSmall)
            }
        }
    }
}

@Composable
fun EditProfileScreenContent(profileData: ProfileData) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White)

    ) {
        Spacer(modifier = Modifier.height(15.dp))
        Box(modifier = Modifier
            .width(120.dp)
            .height(120.dp)
            .clip(RoundedCornerShape(16.dp))
            .align(Alignment.CenterHorizontally)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.avatar_1),
                contentDescription = "profile picture",
                contentScale = ContentScale.FillWidth
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        EditProfileInfoField("Имя", profileData.name)
        EditProfileInfoField("Username", profileData.username)
        EditProfileInfoField("Телефон", "+" + profileData.phone)
        EditProfileInfoField("Дата рождения", profileData.birthday)
    }
}

@Composable
fun EditProfileInfoField(key: String, value: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp, horizontal = 10.dp)
        .background(Color.White)) {
        Text(
            text = "$key: ",
            style = Typography.bodyMedium,
        )
        OutlinedTextField(value = ".", onValueChange = {})
    }
}