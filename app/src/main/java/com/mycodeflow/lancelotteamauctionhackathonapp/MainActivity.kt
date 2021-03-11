package com.mycodeflow.lancelotteamauctionhackathonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.SampleFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.utils.FragsNav

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //navigation
        if (savedInstanceState == null){
            navigateTo(FragsNav.HS)
        }
    }

    private fun navigateTo(fragment: FragsNav) {
        when(fragment){
            FragsNav.HS -> {
                startFragment(SampleFragment.newInstance())
            }
        }
    }

    private fun startFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment)
                .addToBackStack(fragment::class.java.name)
                .commit()
    }


}