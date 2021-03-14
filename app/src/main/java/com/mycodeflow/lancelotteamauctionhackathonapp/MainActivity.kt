package com.mycodeflow.lancelotteamauctionhackathonapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.BaseFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.ads.AdsListFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.ads.AdvertisementDetailFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.authorization.LoginFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.authorization.RegistrationFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.create.NewItemFirstPageFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.create.NewItemSecondPageFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.create.NewItemThirdPageFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.utils.AndroidNotifications
import com.mycodeflow.lancelotteamauctionhackathonapp.utils.FragsNav
import com.mycodeflow.lancelotteamauctionhackathonapp.utils.Notifications

class MainActivity : AppCompatActivity(), BaseFragment.HomeScreenActions,
    AdsListFragment.ButtonClickListener {
    private val notifications: Notifications by lazy { AndroidNotifications() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notifications.initialize(this)

        //navigation
        if (savedInstanceState == null){
            navigateTo(FragsNav.LS)
//            navigateToDetails("FQyYWGehtMv1trh3OmiO")
            intent?.let(::handleIntent)
            notifications.dismissAll()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            handleIntent(intent)
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
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                .addToBackStack(fragment::class.java.name)
                .commit()
    }

    override fun backPageTransaction() {
        supportFragmentManager.popBackStack()
    }

    override fun forwardPageTransaction(frag: FragsNav) {
        navigateTo(frag)
    }

    override fun navigateToDetails(adId: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, AdvertisementDetailFragment.newInstance(adId))
            .addToBackStack(AdvertisementDetailFragment::class.java.name)
            .commit()
    }

    override fun createNewAd() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, NewItemFirstPageFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun handleIntent(intent: Intent) {
        when (intent.action) {
            Intent.ACTION_VIEW -> {
                val id = intent.data?.lastPathSegment
                if (id != null) {
                    navigateToDetails(id)
                    notifications.dismiss(id)
                }
            }
        }
    }
}