package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.Advertisement
import com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository.AdvCreationRepository
import com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository.AdvDetailRepository
import com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository.LoginRegisterRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val advDetailRepository: AdvDetailRepository
) : ViewModel() {
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(this::class.java.simpleName, "CoroutineExceptionHandler:$throwable")
    }

    private val mutableAd = MutableLiveData<Advertisement>()
    val ad: LiveData<Advertisement> get() = mutableAd

    fun loadAd(adId: String) {
        Log.d("ad_d", "${mutableAd.value} adId $adId")
        viewModelScope.launch(coroutineExceptionHandler) {
            mutableAd.value = advDetailRepository.getAdvertisementById(adId)
        }
    }

    fun registerOnAd(adId: String) {

    }
}