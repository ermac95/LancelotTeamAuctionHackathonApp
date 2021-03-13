package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.authorization

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.mycodeflow.lancelotteamauctionhackathonapp.MyApp
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.BaseFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.BaseViewModelFactory
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.LoginRegisterViewModel
import com.mycodeflow.lancelotteamauctionhackathonapp.utils.FragsNav
import javax.inject.Inject

class RegistrationFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory
    private lateinit var viewModel : LoginRegisterViewModel

    private var listener: HomeScreenActions? = null
    private lateinit var registerButton: Button
    private lateinit var userEmail: EditText
    private lateinit var userPass: EditText
    private lateinit var userConfirmPass: EditText
    private lateinit var login_button: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HomeScreenActions){
            listener = context
        }
        (requireActivity().application as MyApp).appComponent.inject(this)
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
        setUpModel()
    }

    private fun setupViews(view: View) {
        userEmail = view.findViewById(R.id.editTextEmail)
        userPass = view.findViewById(R.id.editTextPassword)
        userConfirmPass = view.findViewById(R.id.editTextConfPassword)
        registerButton = view.findViewById(R.id.register_button)
        login_button = view.findViewById(R.id.login_button)
        login_button.setOnClickListener {
            openFragment(FragsNav.LS)
        }
    }

    private fun setUpModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginRegisterViewModel::class.java)
        viewModel.currentUser.observe(this.viewLifecycleOwner, {
            openFragment(FragsNav.LS)
        })
    }

    private fun setupRegistration() {
        registerButton.setOnClickListener{
            when {
                TextUtils.isEmpty(userEmail.text.toString()) -> {
                    userEmail.error = "Please enter your email"
                    return@setOnClickListener
                }
                TextUtils.isEmpty(userPass.text.toString()) -> {
                    userPass.error = "Please enter your password"
                    return@setOnClickListener
                }
                TextUtils.isEmpty(userConfirmPass.text.toString()) -> {
                    userConfirmPass.error = "Please confirm your password"
                    return@setOnClickListener
                }
                else -> viewModel.register(userEmail.text.toString(), userPass.text.toString())
            }
        }
    }

    private fun openFragment(frag: FragsNav){
        listener?.forwardPageTransaction(frag)
    }

    companion object {
        fun newInstance() = RegistrationFragment()
    }
}