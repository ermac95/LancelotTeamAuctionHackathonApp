package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.Advertisement
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.ItemImage
import com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository.AdvCreationRepository
import kotlinx.coroutines.launch

import javax.inject.Inject

class NewItemViewModel @Inject constructor(
    private val advCreationRepository: AdvCreationRepository
) : ViewModel() {

    private val coroutineScope = viewModelScope

    private val mutableAdsList = MutableLiveData<List<Advertisement>>(emptyList())
    val adsList: LiveData<List<Advertisement>> get() = mutableAdsList


    fun setFirstPageData(images: List<ItemImage>, title: String, initialBet: Float, betStep: Float) {
        coroutineScope.launch {
            advCreationRepository.loadFirstPageData(images, title, initialBet, betStep)
        }
    }

    fun setSecondPageData(description: String) {
        coroutineScope.launch {
            advCreationRepository.loadSecondPageData(description)
        }
    }

    fun setSecondPageDataAndPost(date: String, time: String) {
        coroutineScope.launch{
            advCreationRepository.loadThirdPageDataAndPost(date, time)
        }
    }

    fun updateAdvertisementData(){
        coroutineScope.launch {
            advCreationRepository.getAdvertisementList()
        }
    }
}