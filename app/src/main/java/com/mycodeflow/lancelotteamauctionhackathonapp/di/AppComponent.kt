package com.mycodeflow.lancelotteamauctionhackathonapp.di

import android.content.Context
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.authorization.LoginFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SampleModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(frag: LoginFragment)

}