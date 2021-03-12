package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.create

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.utils.FragsNav

class NewItemSecondPageFragment : Fragment() {

    private lateinit var backButton: ImageView
    private lateinit var nextButton: Button
    private var listener: NewItemFirstPageFragment.HomeScreenActions? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewItemFirstPageFragment.HomeScreenActions){
            listener = context
        }
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
    }

    private fun setupViews(view: View){
        //back button initialization
        backButton = view.findViewById(R.id.back_button)
        nextButton = view.findViewById(R.id.continue_button)
    }

    private fun setupListeners() {
        //back button navigation back
        backButton.setOnClickListener{
            listener?.backPageTransaction()
        }
        nextButton.setOnClickListener{
            listener?.forwardPageTransaction(FragsNav.NI3)
        }
    }

    companion object {
        fun newInstance() = NewItemSecondPageFragment()
    }

}