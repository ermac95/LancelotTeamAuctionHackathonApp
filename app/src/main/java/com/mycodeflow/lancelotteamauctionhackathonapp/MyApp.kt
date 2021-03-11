package com.mycodeflow.lancelotteamauctionhackathonapp

import android.app.Application
import com.mycodeflow.lancelotteamauctionhackathonapp.di.AppComponent
import com.mycodeflow.lancelotteamauctionhackathonapp.di.DaggerAppComponent

class MyApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger(){
        appComponent = DaggerAppComponent.factory()
            .create(applicationContext)
    }

}