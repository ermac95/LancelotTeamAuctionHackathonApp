package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository.LoginRegisterRepository
import javax.inject.Inject

class SampleViewModel @Inject constructor(
    val advListRepository: LoginRegisterRepository
    ): ViewModel() {
        //for requesting data from repository - list of advertisements
}