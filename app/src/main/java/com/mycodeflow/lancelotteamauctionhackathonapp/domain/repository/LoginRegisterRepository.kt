package com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRegisterRepository {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val fireStore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    private val userMutableLiveData = MutableLiveData<FirebaseUser>()
    val currentUser: LiveData<FirebaseUser> get() = userMutableLiveData

    suspend fun login(email: String, password: String) = withContext(Dispatchers.IO){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    userMutableLiveData.postValue(firebaseAuth.currentUser)
                } else {
                    Log.d("myLogs", "Failed to login, try again")
                }
            }
    }

    suspend fun register(email: String, password: String) = withContext(Dispatchers.IO) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    userMutableLiveData.postValue(firebaseAuth.currentUser)
                    //val currentUserDb = dataBaseReference?.child(currentUser.uid)
                    // currentUserDb?.child("user_name")?.setValue(userName.text.toString())
                    // currentUserDb?.child("user_email")?.setValue(userEmail.text.toString())
                    //openFragment(FragsNav.LS)
                } else {
                    Log.d("myLogs", "Failed to login, try again")
                }
            }
    }
}