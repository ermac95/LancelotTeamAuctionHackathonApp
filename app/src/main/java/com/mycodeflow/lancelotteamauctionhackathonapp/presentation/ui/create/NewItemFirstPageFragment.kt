package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.create

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.BaseFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.utils.FragsNav

class NewItemFirstPageFragment : BaseFragment() {

    private var listener: HomeScreenActions? = null
    private lateinit var backButton: ImageView
    private lateinit var nextButton: Button
    private lateinit var pictureRecycler: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HomeScreenActions){
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_item_first_page, container, false)
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
        //initializing slider
        pictureRecycler = view.findViewById(R.id.pictures_slider)
    }

    private fun setupListeners() {
        //back button navigation back
        backButton.setOnClickListener{
            listener?.backPageTransaction()
        }
        nextButton.setOnClickListener{
            listener?.forwardPageTransaction(FragsNav.NI2)
        }
    }


    companion object {
        fun newInstance() = NewItemFirstPageFragment()
    }
}