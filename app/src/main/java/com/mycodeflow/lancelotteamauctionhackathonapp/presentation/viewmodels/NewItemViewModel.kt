package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.ItemImage
import com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository.AdvCreationRepository
import com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository.LoginRegisterRepository
import javax.inject.Inject

class NewItemViewModel @Inject constructor(
    //TODO: Заменить репозиторий
    private val advCreationRepository: AdvCreationRepository
) : ViewModel() {

    private var _images: List<ItemImage>? = listOf()
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
        //TODO Repository.post
    }
}