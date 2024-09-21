package com.example.greenhousechat.data

import com.example.greenhousechat.R

class ContactsData {
    fun getContactsList(): List<ContactProfile> {
        return listOf(
            ContactProfile(
                id = 0,
                imageResourceId = R.drawable.avatar_0,
                name = "John",
                message = "Здарова"
            ),
            ContactProfile(
                id = 1,
                imageResourceId = R.drawable.avatar_1,
                name = "Mike",
                message = "Я нашел твой профиль"
            ),
            ContactProfile(
                id = 2,
                imageResourceId = R.drawable.avatar_2,
                name = "Emma",
                message = "Такие дела"
            ),
            ContactProfile(
                id = 3,
                imageResourceId = R.drawable.avatar_3,
                name = "Anna",
                message = "Ты где"
            ),
            ContactProfile(
                id = 4,
                imageResourceId = R.drawable.avatar_4,
                name = "Alex",
                message = "Я похавал"
            ),
            ContactProfile(
                id = 5,
                imageResourceId = R.drawable.avatar_5,
                name = "May",
                message = "Возьмешься за это?"
            ),
            ContactProfile(
                id = 6,
                imageResourceId = R.drawable.avatar_6,
                name = "Kat",
                message = "Надо с этим разобраться"
            ),
            ContactProfile(
                id = 7,
                imageResourceId = R.drawable.avatar_7,
                name = "Pasha",
                message = "Где это будет"
            ),
            ContactProfile(
                id = 8,
                imageResourceId = R.drawable.avatar_8,
                name = "Kat",
                message = "Возьмите на работу пж)"
            ),
        )
    }
}