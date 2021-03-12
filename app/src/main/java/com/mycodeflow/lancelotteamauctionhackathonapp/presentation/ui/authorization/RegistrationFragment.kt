package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.authorization

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.create.NewItemFirstPageFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.utils.FragsNav

class RegistrationFragment : Fragment() {

    private var listener: NewItemFirstPageFragment.HomeScreenActions? = null
    private lateinit var registerButton: Button
    private lateinit var userName: EditText
    private lateinit var userEmail: EditText
    private lateinit var userPass: EditText
    private lateinit var userConfirmPass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewItemFirstPageFragment.HomeScreenActions){
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupRegistration()
    }

    private fun setupViews(view: View) {
        userName = view.findViewById(R.id.editTextName)
        userEmail = view.findViewById(R.id.editTextEmail)
        userPass = view.findViewById(R.id.editTextPassword)
        userConfirmPass = view.findViewById(R.id.editTextConfPassword)
        registerButton = view.findViewById(R.id.register_button)
    }


    private fun setupRegistration() {
        registerButton.setOnClickListener{
            //TODO
        }
    }

    private fun openFragment(frag: FragsNav){
        listener?.forwardPageTransaction(frag)
    }

    companion object {
        fun newInstance() = RegistrationFragment()
    }
}