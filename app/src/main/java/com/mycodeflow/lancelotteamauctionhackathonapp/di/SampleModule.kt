package com.mycodeflow.lancelotteamauctionhackathonapp.di

import com.mycodeflow.lancelotteamauctionhackathonapp.domain.datasource.SampleDataSource
import com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository.AdvCreationRepository
import com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository.AdvDetailRepository
import com.mycodeflow.lancelotteamauctionhackathonapp.domain.repository.LoginRegisterRepository
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.BaseViewModelFactory
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.NewItemViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SampleModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(advListRepository: LoginRegisterRepository, advCreationRepository: AdvCreationRepository, advDetailRepository: AdvDetailRepository): BaseViewModelFactory {
        return BaseViewModelFactory(advListRepository, advCreationRepository, advDetailRepository)
    }

    @Provides
    @Singleton
    fun provideDataSource(): SampleDataSource{
        return SampleDataSource()
    }

}

