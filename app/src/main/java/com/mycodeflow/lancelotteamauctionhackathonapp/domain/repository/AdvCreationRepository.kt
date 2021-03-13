package com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.Advertisement
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.ItemImage
import javax.inject.Inject

class AdvCreationRepository @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    val fireStore: FirebaseFirestore
    ) {

    private val collection = fireStore.collection("advertisements")

    suspend fun uploadAdvToDataBase(
        _title: String,
        _poster: Uri,
        _images: List<ItemImage>,
        _price: String,
        _description: String,
        _date: String,
        _time: String
    ): Advertisement {
        val uniqueId = createUniqueId()
        val currentUserUid = firebaseAuth.currentUser.uid
        val poster = _images[0].bgImage
        val advertisement = Advertisement(
            id = uniqueId,
            ownerUid = currentUserUid,
            title = _title,
            poster = poster,
            images = _images,
            price = _price,
            description = _description,
            date = _date,
            time = _time,
            participators = null
        )
        loadAdvToDatabase(advertisement)
        return advertisement
    }

    private fun loadAdvToDatabase(advertisement: Advertisement) {
        collection.document(advertisement.id).set(advertisement)
    }

    private fun createUniqueId(): String {
        return collection.document().id
    }
}