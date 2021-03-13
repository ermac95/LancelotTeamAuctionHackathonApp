package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository.LoginRegisterRepository
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class BaseViewModelFactory @Inject constructor(
    private val loginRegisterRepository: LoginRegisterRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass){
        LoginRegisterViewModel::class.java -> LoginRegisterViewModel(loginRegisterRepository)
        else -> throw IllegalArgumentException("wrong dependencies")
    } as T
}