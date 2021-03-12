package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.authorization

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.create.NewItemFirstPageFragment
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.viewmodels.LoginRegisterViewModel
import com.mycodeflow.lancelotteamauctionhackathonapp.utils.FragsNav

class ProfileFragment : Fragment() {

    private lateinit var viewModel : LoginRegisterViewModel

    private lateinit var auth: FirebaseAuth
    private var dataBaseReference: DatabaseReference? = null
    private var database: FirebaseDatabase? = null
    private lateinit var userName: TextView
    private lateinit var userEmail: TextView
    private lateinit var logOutButton: Button
    private var listener: NewItemFirstPageFragment.HomeScreenActions? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        dataBaseReference = database?.reference?.child("profile")
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
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupListeners()
        loadProfile()
    }

    private fun setupViews(view: View) {
        userName = view.findViewById(R.id.profile_user_name)
        userEmail = view.findViewById(R.id.profile_user_email)
        logOutButton = view.findViewById(R.id.logout_button)
    }

    private fun setupListeners() {
        logOutButton.setOnClickListener{
            auth.signOut()
            listener?.forwardPageTransaction(FragsNav.LS)
        }
    }

    private fun loadProfile(){
        val user = auth.currentUser
        val userReference = user?.uid?.let { dataBaseReference?.child(it) }
        userReference?.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userName.text = snapshot.child("user_name").value.toString()
                userEmail.text = snapshot.child("user_email").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
}