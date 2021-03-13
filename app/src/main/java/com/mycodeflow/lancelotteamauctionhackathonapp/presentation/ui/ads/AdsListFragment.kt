package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.ads

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mycodeflow.lancelotteamauctionhackathonapp.MyApp
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.BaseViewModelFactory
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.NewItemViewModel
import javax.inject.Inject

class AdsListFragment : Fragment() {

    private lateinit var newAdButton: FloatingActionButton
    private lateinit var rvAdsList: RecyclerView
    private var listener: ButtonClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ButtonClickListener){
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ads_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //recycler initialization
        rvAdsList = view.findViewById(R.id.ads_list)
        rvAdsList.adapter = AdsListAdapter()
        //bottom navigation
        view.findViewById<BottomNavigationView>(R.id.bottom_nav_view).apply {
            background = null
            menu.getItem(2).isEnabled = false
        }
        //new ad button configuration
        newAdButton = view.findViewById(R.id.new_ad_button)
        newAdButton.setOnClickListener{
            listener?.createNewAd()
        }
    }

    companion object {
        fun newInstance() = AdsListFragment()
    }

    interface ButtonClickListener{
        fun createNewAd()
    }
}