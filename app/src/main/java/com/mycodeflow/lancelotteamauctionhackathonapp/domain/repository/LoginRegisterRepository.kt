package com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRegisterRepository @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    val fireStore: FirebaseFirestore
) {

    private val userMutableLiveData = MutableLiveData<FirebaseUser>()
    val currentUser: LiveData<FirebaseUser> get() = userMutableLiveData

    suspend fun login(email: String, password: String) = withContext(Dispatchers.IO){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    userMutableLiveData.postValue(firebaseAuth.currentUser)
                } else {
                    Log.d("myLogs", "Failed to login, try again")
                    Log.d("Error", it.exception?.message.toString());
                }
            }
    }

    suspend fun register(email: String, password: String) = withContext(Dispatchers.IO) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    userMutableLiveData.postValue(firebaseAuth.currentUser)
                    //val newUser = getUserCreated(firebaseAuth.currentUser.uid)
                    //val currentUserDb = dataBaseReference?.child(currentUser.uid)
                    // currentUserDb?.child("user_name")?.setValue(userName.text.toString())
                    // currentUserDb?.child("user_email")?.setValue(userEmail.text.toString())
                    //openFragment(FragsNav.LS)
                } else {
                    Log.d("myLogs", "Failed to registration, try again")
                    Log.d("Error", it.exception?.message.toString());
                }
            }
    }

    /*
    private fun getUserCreated(email: String, password: String): UserModel {
        return UserModel(
            id = firebaseAuth.currentUser.uid,
            name = "John Doe",
            email = email,
            pass = pass,
            profilePic = null,
            adsList = null
        )
    }
     */
}