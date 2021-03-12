package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository.LoginRegisterRepository
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






    fun login(email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()){

            return
        }


        viewModelScope.launch(coroutineExceptionHandler) {
            loginRegisterRepository.login(email, password)
        }


    }

    fun register(email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()){
            return
        }


        viewModelScope.launch(coroutineExceptionHandler) {
            loginRegisterRepository.register(email, password)

        }
    }

}