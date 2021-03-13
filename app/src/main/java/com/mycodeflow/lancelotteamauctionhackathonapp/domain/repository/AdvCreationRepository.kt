package com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.storage.FirebaseStorage
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.Advertisement
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.ItemImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AdvCreationRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage
    ) {

    private var rTitle: String = ""
    private var rImages: List<String> = emptyList()
    private val listOfUrls: ArrayList<String> = ArrayList()
    private var rPrice: Float = 0.0f
    private var rBetStep: Float = 0.0f
    private var rDescription: String = ""
    private var rDate: String? = ""
    private var rTime: String? = ""

    private suspend fun createAdvertismentModel(
        _title: String?,
        _images: List<String>,
        _price: Float,
        _betStep: Float,
        _description: String?,
        _date: String?,
        _time: String?
    ): Advertisement = withContext(Dispatchers.IO) {
        val uniqueId = createUniqueId()
        val currentUserUid = firebaseAuth.currentUser.uid
        val imageForPoster = _images[0]
        val advertisement = Advertisement(
            id = uniqueId,
            ownerUid = currentUserUid,
            title = _title,
            poster = imageForPoster,
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

    private fun createUniqueId(): String {
        return fireStore.collection("advertisements").document().id
    }

    suspend fun loadFirstPageData(images: List<ItemImage>, title: String, initialBet: Float, betStep: Float) = withContext(Dispatchers.IO){
        Log.d("myLogs", "first function go")
        val job = uploadImagesToStorage(images)
        Log.d("myLogs", "first function finished")
        Log.d("myLogs", "second function started")
        assignValues(listOfUrls, title, initialBet, betStep)
        Log.d("myLogs", "second function finished")
    }

    private suspend fun assignValues(
        listOfUrls: ArrayList<String>,
        title: String,
        initialBet: Float,
        betStep: Float
    ) = withContext(Dispatchers.IO){
        rImages = listOfUrls
        rTitle = title
        rPrice = initialBet
        rBetStep = betStep
        Log.d("myLogs", "rImages = $rImages")
    }

    private suspend fun uploadImagesToStorage(images: List<ItemImage>) = withContext(Dispatchers.IO) {
        val ref = firebaseStorage.reference
        images.forEach { image ->
            if (image.bgImage != null){
                //uploading image to storage
                val file = Uri.parse(image.bgImage)
                val imageRef = ref.child("images/${file.lastPathSegment}")
                val uploadTask = imageRef.putFile(file)
                uploadTask.addOnFailureListener {
                    Log.d("myLogs", "failed to upload image cuz ${it.message}")
                }.addOnSuccessListener {
                    Log.d("myLogs", "Successful image upload")
                }
                //getting uploaded image downloadUrl
                val urlTask = uploadTask.continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it
                        }
                    }
                    imageRef.downloadUrl
                }.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val downloadUri = task.result
                        if (downloadUri != null){
                            listOfUrls.add(downloadUri.toString())
                            Log.d("myLogs", "List of url = $listOfUrls")
                        }
                    } else {
                        // Handle failures
                        // ...
                    }
                }.await()
            }
        }
        Log.d("myLogs", "End of coroutine")
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
}