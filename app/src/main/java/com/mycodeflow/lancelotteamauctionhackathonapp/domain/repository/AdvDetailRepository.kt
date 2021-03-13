package com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.Advertisement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AdvDetailRepository @Inject constructor(
    private val fireStore: FirebaseFirestore
) {

    suspend fun getAdvertisementById(advId: String): Advertisement? = withContext(Dispatchers.IO) {
        val data = loadAdvFromFireStore(advId)
        Log.d("ad_d", "${data.toString()}")
        val advertisement = data.toObject<Advertisement>()
        Log.d("ad_d", "${advertisement.toString()}")
        advertisement
    }

    private suspend fun loadAdvFromFireStore(advId: String): DocumentSnapshot = withContext(Dispatchers.IO) {
        fireStore.collection("advertisements")
            .document(advId)
            .get()
            .await()
    }
}