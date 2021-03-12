package com.mycodeflow.lancelotteamauctionhackathonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.ads.AdsListFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.authorization.LoginFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.authorization.RegistrationFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.create.NewItemFirstPageFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.create.NewItemSecondPageFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.create.NewItemThirdPageFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.utils.FragsNav

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //navigation
        if (savedInstanceState == null){
            navigateTo(FragsNav.LS)
        }
    }

    private fun navigateTo(fragment: FragsNav) {
        when(fragment){
            FragsNav.AS -> {
                startFragment(AdsListFragment.newInstance())
            }
            FragsNav.NI1 -> {
                startFragment(NewItemFirstPageFragment.newInstance())
            }
            FragsNav.NI2 -> {
                startFragment(NewItemSecondPageFragment.newInstance())
            }
            FragsNav.NI3 -> {
                startFragment(NewItemThirdPageFragment.newInstance())
            }
            FragsNav.LS -> {
                startFragment(LoginFragment.newInstance())
            }
            FragsNav.RS -> {
                startFragment(RegistrationFragment.newInstance())
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