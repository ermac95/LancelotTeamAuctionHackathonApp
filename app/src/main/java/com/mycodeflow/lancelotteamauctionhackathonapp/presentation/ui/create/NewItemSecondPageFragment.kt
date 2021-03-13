package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.create

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mycodeflow.lancelotteamauctionhackathonapp.MyApp
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.BaseFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.BaseViewModelFactory
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.NewItemViewModel
import com.mycodeflow.lancelotteamauctionhackathonapp.utils.FragsNav
import javax.inject.Inject

class NewItemSecondPageFragment : BaseFragment() {

    private lateinit var backButton: ImageView
    private lateinit var nextButton: Button
    private lateinit var description : EditText
    private var listener: HomeScreenActions? = null

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory
    private lateinit var viewModel : NewItemViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HomeScreenActions){
            listener = context
        }
        (requireActivity().application as MyApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_new_item_second_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupListeners()
        setUpViewModel()
    }

    private fun setupViews(view: View){
        //back button initialization
        backButton = view.findViewById(R.id.back_button)
        nextButton = view.findViewById(R.id.continue_button)
        description = view.findViewById(R.id.field_detailed_description)
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewItemViewModel::class.java)
    }

    private fun setupListeners() {
        //back button navigation back
        backButton.setOnClickListener{
            listener?.backPageTransaction()
        }
        nextButton.setOnClickListener{
            val descriptionText = description.text.toString()
            when {
                TextUtils.isEmpty(descriptionText) -> {
                    description.error = "Please enter value"
                    return@setOnClickListener
                }
                else -> {
                    viewModel.setSecondPageData(descriptionText)
                    Log.d("viewModel", "description - $descriptionText")
                    listener?.forwardPageTransaction(FragsNav.NI3)
                }
            }
        }
    }

    companion object {
        fun newInstance() = NewItemSecondPageFragment()
    }

}