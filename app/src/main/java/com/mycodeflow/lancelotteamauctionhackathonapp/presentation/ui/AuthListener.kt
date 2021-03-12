package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}