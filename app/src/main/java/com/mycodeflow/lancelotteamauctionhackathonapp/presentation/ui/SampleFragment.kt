package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mycodeflow.lancelotteamauctionhackathonapp.MyApp
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.LoginRegisterViewModel
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.SampleViewModelFactory
import javax.inject.Inject

class SampleFragment : Fragment() {

    @Inject lateinit var viewModelFactory: SampleViewModelFactory
    private lateinit var viewModel: LoginRegisterViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(LoginRegisterViewModel::class.java)
    }

    companion object {
        fun newInstance() = SampleFragment()
    }
}