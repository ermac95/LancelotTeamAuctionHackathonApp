package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository.SampleRepository
import java.lang.IllegalArgumentException
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class SampleViewModelFactory @Inject constructor(
    private val advListRepository: SampleRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass){
        LoginRegisterViewModel::class.java -> LoginRegisterViewModel(advListRepository)
        else -> throw IllegalArgumentException("wrong dependencies")
    } as T
}