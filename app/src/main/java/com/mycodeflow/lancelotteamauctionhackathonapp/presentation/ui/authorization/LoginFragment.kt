package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.authorization

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.SampleViewModel
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.SampleViewModelFactory
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: SampleViewModelFactory
    private lateinit var viewModel : SampleViewModel

    private lateinit var loginButton: Button
    private lateinit var registerButton: TextView
    private lateinit var userName: EditText
    private lateinit var userPass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
    }

    private fun setupViews(view: View) {
        loginButton = view.findViewById(R.id.login_button)
        registerButton = view.findViewById(R.id.register_now_button)
        userName = view.findViewById(R.id.login_user_name)
        userPass = view.findViewById(R.id.login_user_password)
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}