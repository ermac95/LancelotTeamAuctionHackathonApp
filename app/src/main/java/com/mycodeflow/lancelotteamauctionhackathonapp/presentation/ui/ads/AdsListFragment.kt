package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.ads

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mycodeflow.lancelotteamauctionhackathonapp.MyApp
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.Advertisement
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.BaseFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.BaseViewModelFactory
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.NewItemViewModel
import javax.inject.Inject

class AdsListFragment : Fragment() {

    private lateinit var newAdButton: FloatingActionButton
    private lateinit var rvAdsList: RecyclerView
    private var listener: ButtonClickListener? = null
    private var adListener: BaseFragment.HomeScreenActions? = null

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory
    private lateinit var viewModel : NewItemViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ButtonClickListener){
            listener = context
        }
        if (context is BaseFragment.HomeScreenActions){
            adListener = context
        }
        (requireActivity().application as MyApp).appComponent.inject(this)
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
        rvAdsList.adapter = AdsListAdapter(adListener)
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
        setUpViewModel()
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewItemViewModel::class.java)
        viewModel.updateAdvertisementData()
        Log.d("ads", viewModel.adsList.value.toString())

        viewModel.adsList.observe(this.viewLifecycleOwner, {
            updateAds(it)
        })
    }

    private fun updateAds(ads: List<Advertisement>) {
        Log.d("ads", "${ads.size}")
        (rvAdsList.adapter as? AdsListAdapter)?.onBindAds(ads)
    }

    companion object {
        fun newInstance() = AdsListFragment()
    }

    interface ButtonClickListener{
        fun createNewAd()
    }
}