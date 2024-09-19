package com.example.greenhousechat.data

class ContactsData {
    fun getContactsList(): List<ContactProfile> {
        return listOf(
            ContactProfile(
                id = 0,
                photoId = 0,
                name = "John",
                message = "Здарова"
            ),
            ContactProfile(
                id = 1,
                photoId = 0,
                name = "Mike",
                message = "Я нашел твой профиль"
            ),
            ContactProfile(
                id = 2,
                photoId = 0,
                name = "Emma",
                message = "Такие дела"
            ),
            ContactProfile(
                id = 3,
                photoId = 0,
                name = "Anna",
                message = "Ты где"
            ),
            ContactProfile(
                id = 4,
                photoId = 0,
                name = "Alex",
                message = "Я похавал"
            ),
            ContactProfile(
                id = 5,
                photoId = 0,
                name = "May",
                message = "Возьмешься за это?"
            ),
            ContactProfile(
                id = 6,
                photoId = 0,
                name = "Kat",
                message = "Надо с этим разобраться"
            ),
        )
    }
}