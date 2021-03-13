package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.Advertisement
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.ItemImage
import com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository.AdvCreationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import javax.inject.Inject

class NewItemViewModel @Inject constructor(
    private val advCreationRepository: AdvCreationRepository
) : ViewModel() {

    private val coroutineScope = viewModelScope

    private val mutableAdvertisementItem = MutableLiveData<Advertisement>()
    val advertisementItem: LiveData<Advertisement> get() = mutableAdvertisementItem

    private var _images: List<ItemImage>? = null
    private var _title: String? = null
    private var _initialBet: Float = 0.0f
    private var _betStep: Float = 0.0f
    private var _description: String? = null
    private var _date: String? = null
    private var _time: String? = null

    fun setFirstPageData(images: List<ItemImage>, title: String, initialBet: Float, betStep: Float) {
        _images = images
        _title = title
        _initialBet = initialBet
        _betStep = betStep
    }

    fun setSecondPageData(description: String) {
        _description = description
    }

    fun setSecondPageDataAndPost(date: String, time: String) {
        _date = date
        _time = time
        coroutineScope.launch{
            mutableAdvertisementItem.value = advCreationRepository.uploadAdvToDataBase(
                _title,
                _images,
                _initialBet,
                _betStep,
                _description,
                _date,
                _time
            )
        }
    }
}