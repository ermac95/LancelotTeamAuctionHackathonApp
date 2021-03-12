package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.authorization

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.mycodeflow.lancelotteamauctionhackathonapp.MyApp
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.create.NewItemFirstPageFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.LoginRegisterViewModel
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.BaseViewModelFactory
import com.mycodeflow.lancelotteamauctionhackathonapp.utils.FragsNav
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory
    private lateinit var viewModel : LoginRegisterViewModel

    private lateinit var loginButton: Button
    private lateinit var registerButton: TextView
    private lateinit var userName: EditText
    private lateinit var userPass: EditText
    private var listener: NewItemFirstPageFragment.HomeScreenActions? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewItemFirstPageFragment.HomeScreenActions){
            listener = context
        }
        (requireActivity().application as MyApp).appComponent.inject(this)
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
        setupLogin()
        setUpModel()
    }

    fun setUpModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginRegisterViewModel::class.java)
        viewModel.currentUser.observe(this.viewLifecycleOwner, {
            openFragment(FragsNav.AS)
        })
    }

    private fun setupViews(view: View) {
        loginButton = view.findViewById(R.id.login_button)
        registerButton = view.findViewById(R.id.register_now_button)
        userName = view.findViewById(R.id.login_user_name)
        userPass = view.findViewById(R.id.login_user_password)
        registerButton.setOnClickListener {
            Log.d("clicklist", "")
            openFragment(FragsNav.RS)
        }
    }

    private fun setupLogin() {
        loginButton.setOnClickListener {
            if (TextUtils.isEmpty(userName.text.toString())) {
                userName.error = "Please enter username"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(userPass.text.toString())) {
                userPass.error = "Please enter user password"
                return@setOnClickListener
            }
            //TODO
//            viewModel.login(userName.text.toString(), userPass.text.toString())
            openFragment(FragsNav.RS)
        }
    }

    private fun openFragment(frag: FragsNav){
        listener?.forwardPageTransaction(frag)
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}