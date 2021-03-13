package com.mycodeflow.lancelotteamauctionhackathonapp.di

import android.content.Context
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.BaseFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.authorization.LoginFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.authorization.RegistrationFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.create.NewItemFirstPageFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.create.NewItemSecondPageFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.create.NewItemThirdPageFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SampleModule::class, FireBaseModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(frag: LoginFragment)

    fun inject(frag: RegistrationFragment)

    fun inject(frag: NewItemFirstPageFragment)

    fun inject(frag: NewItemSecondPageFragment)

    fun inject(frag: NewItemThirdPageFragment)

}