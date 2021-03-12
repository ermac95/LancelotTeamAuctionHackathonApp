package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository.SampleRepository
import java.lang.IllegalArgumentException
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class SampleViewModelFactory @Inject constructor(
    private val advListRepository: SampleRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass){
        SampleViewModel::class.java -> SampleViewModel(advListRepository)
        else -> throw IllegalArgumentException("wrong dependencies")
    } as T
}