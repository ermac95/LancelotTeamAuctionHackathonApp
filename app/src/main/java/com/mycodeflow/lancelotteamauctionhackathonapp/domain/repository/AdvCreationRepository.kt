package com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.Advertisement
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.ItemImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.concurrent.ThreadPoolExecutor
import javax.inject.Inject

class AdvCreationRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
    ) {

    private var rTitle: String = ""
    private var rImages: List<ItemImage> = emptyList()
    private var rPrice: Float = 0.0f
    private var rBetStep: Float = 0.0f
    private var rDescription: String = ""
    private var rDate: String? = ""
    private var rTime: String? = ""

    private suspend fun createAdvertismentModel(
        _title: String?,
        _images: List<ItemImage>?,
        _price: Float,
        _betStep: Float,
        _description: String?,
        _date: String?,
        _time: String?
    ): Advertisement = withContext(Dispatchers.IO) {
        val uniqueId = createUniqueId()
        val currentUserUid = firebaseAuth.currentUser.uid
        val poster = _images?.get(0)?.bgImage
        val advertisement = Advertisement(
            id = uniqueId,
            ownerUid = currentUserUid,
            title = _title,
            poster = poster,
            images = _images,
            price = _price,
            betStep = _betStep,
            description = _description,
            date = _date,
            time = _time,
            participators = null
        )
        advertisement
    }

    suspend fun loadFirstPageData(images: List<ItemImage>, title: String, initialBet: Float, betStep: Float) = withContext(Dispatchers.IO){
        rImages = images
        rTitle = title
        rPrice = initialBet
        rBetStep = betStep
    }

    suspend fun loadSecondPageData(description: String) = withContext(Dispatchers.IO){
        rDescription = description
        Log.d("myLogs", "images = $rImages, rTitle = $rTitle, rPrice = $rPrice, rBetStep = $rBetStep, description = $description")
    }

    suspend fun loadThirdPageDataAndPost(date: String, time: String) = withContext(Dispatchers.IO){
        rDate = date
        rTime = time
        val advertisement = createAdvertismentModel(rTitle, rImages, rPrice, rBetStep, rDescription, rDate, rTime)
        loadAdvToDataBase(advertisement)
    }

    private suspend fun loadAdvToDataBase(advertisement: Advertisement) = withContext(Dispatchers.IO){
        val collection = fireStore.collection("advertisements")
        collection.document()
            .set(advertisement)
            .addOnCompleteListener { Log.d("myLogs", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.d("myLogs", "Error writing document", e) }
    }

    suspend fun getAdvertisementList(): List<Advertisement> = withContext(Dispatchers.IO) {
        val adsList = loadListFromFb()
        val advertisements = adsList.toObjects<Advertisement>()
        Log.d("myLogs", "Ads = $advertisements")
        advertisements
    }

    suspend fun loadListFromFb(): QuerySnapshot = withContext(Dispatchers.IO){
        val snapshot = fireStore
            .collection("advertisements")
            .get()
            .await()
        snapshot
    }

    private fun createUniqueId(): String {
        return fireStore.collection("advertisements").document().id
    }
}