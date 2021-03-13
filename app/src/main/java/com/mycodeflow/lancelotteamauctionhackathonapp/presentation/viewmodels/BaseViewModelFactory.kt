package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository.AdvCreationRepository
import com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository.AdvDetailRepository
import com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository.LoginRegisterRepository
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class BaseViewModelFactory @Inject constructor(
    private val loginRegisterRepository: LoginRegisterRepository,
    private val advCreationRepository: AdvCreationRepository,
    private val advDetailRepository: AdvDetailRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass){
        LoginRegisterViewModel::class.java -> LoginRegisterViewModel(loginRegisterRepository)
        NewItemViewModel::class.java -> NewItemViewModel(advCreationRepository)
        DetailsViewModel::class.java -> DetailsViewModel(advDetailRepository)
        else -> throw IllegalArgumentException("wrong dependencies")
    } as T
}