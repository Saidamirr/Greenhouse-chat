package com.example.greenhousechat.ui

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.greenhousechat.R
import com.example.greenhousechat.data.ProfileData
import com.example.greenhousechat.ui.theme.Typography
import com.example.greenhousechat.viewmodel.AppViewModel

@Composable
fun ProfileScreen(modifier: Modifier = Modifier,
                  appViewModel: AppViewModel,
                  navController: NavHostController
) {
    val profileData: ProfileData = appViewModel.getLocalProfileData()
//    val context = LocalContext.current
//    var pickImageLauncher: ManagedActivityResultLauncher<String, Uri?>? = null
//    if(appViewModel.base64Str != "") {
//        pickImageLauncher = rememberLauncherForActivityResult(
//            contract = ActivityResultContracts.GetContent(),
//        ) {
//                uri: Uri? ->
//            uri?.let {
//                val bitmap = appViewModel.loadBitmapFromUri(it, context)
//                val base64String: String
//                if (bitmap != null) {
//                    base64String = appViewModel.bitmapToBase64(bitmap)
//                } else {
//                    base64String = ""
//                }
//                // Сохраняем base64-строку для дальнейшей работы
//                appViewModel.onImageUpload(base64String)
//            }
//        }
//    }


    Column(modifier = Modifier
        .background(color = Color(236, 250, 235))
        .padding(vertical = 30.dp, horizontal = 15.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ProfileScreenContent(profileData = profileData, appViewModel = appViewModel)

        Column {
            Button(onClick = { appViewModel.onToEditProfile(navController)}) {
                Text(text = "Редактировать", style = Typography.labelSmall)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { appViewModel.signOut(navController)}) {
                Text(text = "Выйти из профиля", style = Typography.labelSmall)
            }
        }
    }
}

@Composable
fun ProfileScreenContent(profileData: ProfileData,
                         appViewModel: AppViewModel
) {

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
                painter = painterResource(id = R.drawable.avatar_1),
                modifier = Modifier.fillMaxSize(),
                contentDescription = "profile picture",
                contentScale = ContentScale.FillWidth
            )

            
        }
        Spacer(modifier = Modifier.height(20.dp))
        ProfileInfoField("Имя", profileData.name)
        ProfileInfoField("Username", profileData.username)
        ProfileInfoField("Телефон", "+" + profileData.phone)
        ProfileInfoField("Дата рождения", profileData.birthday)
        ProfileInfoField("Город", profileData.city)
        ProfileInfoField("О себе", profileData.status)

    }
}

@Composable
fun ProfileInfoField(key: String, value: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp, horizontal = 10.dp)
        .background(Color.White)) {
        Text(
            text = "$key: $value",
            style = Typography.bodyMedium,
        )
    }
}