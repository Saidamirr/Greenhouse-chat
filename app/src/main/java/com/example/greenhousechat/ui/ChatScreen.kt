package com.example.greenhousechat.ui

import android.widget.Space
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.greenhousechat.R
import com.example.greenhousechat.data.ContactProfile
import com.example.greenhousechat.data.ContactsData
import com.example.greenhousechat.ui.theme.Typography
import com.example.greenhousechat.viewmodel.AppViewModel


@Composable
fun ChatScreen(navController: NavHostController, appViewModel: AppViewModel) {
    Column(modifier = Modifier
        .background(color = Color(236, 250, 235))
        .padding(top = 30.dp)
    ) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 20.dp)
        ) {
            AppIcon()
            Text(text = stringResource(id = R.string.app_name), style = Typography.labelLarge,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp))
            Box(modifier = Modifier.clickable { appViewModel.onToProfileButtonClick(navController) }) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Профиль")
            }
        }
        ChatScreenContent()
        
    }
}

@Composable
fun Icon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier.padding(20.dp),
    tint: Color = LocalContentColor.current
) {
    androidx.compose.material3.Icon(
        painter = rememberVectorPainter(imageVector),
        contentDescription = contentDescription,
        modifier = modifier.size(100.dp),
        tint = tint
    )
}

@Composable
fun ChatItem(contactProfile: ContactProfile) {
    Card() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                ContactIcon(contactProfile.imageResourceId)
                ContactInformation(contactProfile.name, contactProfile.message)
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }

@Composable
fun AppIcon(
    @DrawableRes appIcon: Int = R.drawable.ic_launcher_foreground,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(120.dp)
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        painter = painterResource(appIcon),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
fun ContactIcon(
    @DrawableRes contactIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        painter = painterResource(contactIcon),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
fun ContactInformation(
    contactName: String,
    contactMsg: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = contactName,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small)),
            style = Typography.titleLarge

            )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = contactMsg,
            style = Typography.bodyLarge
        )
    }
}



@Composable
fun ChatScreenContent() {
    LazyColumn (
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)

    ) {

        items(ContactsData().getContactsList()) { contact ->
            ChatItem(contactProfile = contact)
        }
    }
}
