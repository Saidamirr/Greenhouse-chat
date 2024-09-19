package com.example.greenhousechat.ui

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.greenhousechat.data.ContactProfile
import com.example.greenhousechat.data.ContactsData


@Composable
fun ChatScreen() {
    ChatScreenContent()
}

@Composable
fun ChatItem(contactProfile: ContactProfile) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)) {
        Row {
            Box(modifier = Modifier
                .width(30.dp)
                .height(30.dp)
                .background(color = androidx.compose.ui.graphics.Color.Black))
            Column {
                Text(text = contactProfile.name)
                Text(text = contactProfile.message)
            }
        }
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
