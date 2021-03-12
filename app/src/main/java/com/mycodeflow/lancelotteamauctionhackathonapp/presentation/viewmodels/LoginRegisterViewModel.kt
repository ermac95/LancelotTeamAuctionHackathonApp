package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository.LoginRegisterRepository
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.AuthListener
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginRegisterViewModel @Inject constructor(
    private val loginRegisterRepository: LoginRegisterRepository
) : ViewModel() {
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(this::class.java.simpleName, "CoroutineExceptionHandler:$throwable")
    }

//    private val _user = MutableLiveData<FirebaseUser>()
//    val user : LiveData<FirebaseUser> get() = _user

    var authListener: AuthListener? = null



    fun login(email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }

        authListener?.onStarted()

        viewModelScope.launch(coroutineExceptionHandler) {
            loginRegisterRepository.login(email, password)
        }


    }

    fun register(email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()){
            authListener?.onFailure("Please input all values")
            return
        }

        authListener?.onStarted()

        viewModelScope.launch(coroutineExceptionHandler) {
            loginRegisterRepository.register(email, password)

        }
    }

}