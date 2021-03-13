package com.mycodeflow.lancelotteamauctionhackathonapp.data.models

import com.google.firebase.firestore.auth.User

data class Advertisement(
    val id: String = "",
    val ownerUid: String = "",
    val title: String? = "",
    val poster: String? = "",
    val images: List<String> = emptyList(),
    val price: Float? = 0f,
    val betStep: Float? = 0f,
    val description: String? = "",
    val date: String? = "",
    val time: String? = "",
    val participators: List<User>? = emptyList()
)