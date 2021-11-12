package com.example.testapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.testapp.DataBase.ViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar_profile.*


class ProfileFragment : Fragment() {

    var navc : NavController?= null
    private lateinit var mUserViewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        mUserViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navc = Navigation.findNavController(view)

        imageButtonAccount.setOnClickListener {
            navc?.navigate(R.id.action_profileFragment_to_mainFragment)

        }

        mUserViewModel.getActiveAccount(true).observe(viewLifecycleOwner,{ a->
            textViewEmail.text = a.email
        })

    }
}