package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mycodeflow.lancelotteamauctionhackathonapp.R

class NewItemFirstPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_item_first_page, container, false)
    }

    companion object {
        fun newInstance() = NewItemFirstPageFragment()
    }
}