package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.mycodeflow.lancelotteamauctionhackathonapp.data.repository.SampleRepository
import javax.inject.Inject

class SampleViewModel @Inject constructor(
    val advListRepository: SampleRepository
    ): ViewModel() {
        //for requesting data from repository - list of advertisements
}