package com.mycodeflow.lancelotteamauctionhackathonapp.di

import com.mycodeflow.lancelotteamauctionhackathonapp.data.repository.SampleRepository
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.SampleViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SampleModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(advListRepository: SampleRepository): SampleViewModelFactory {
        return SampleViewModelFactory(advListRepository)
    }

}

