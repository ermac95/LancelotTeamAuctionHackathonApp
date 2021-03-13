package com.mycodeflow.lancelotteamauctionhackathonapp.data.models

import android.net.Uri
import com.google.firebase.firestore.auth.User

data class Advertisement(
    val id: String,
    val ownerUid: String,
    val title: String,
    val poster: Uri?,
    val images: List<ItemImage>,
    val price: String,
    val description: String,
    val date: String,
    val time: String,
    val participators: List<User>? = null,
)