package com.example.testapp

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.testapp.DataBase.Entity
import com.example.testapp.DataBase.Repository
import com.example.testapp.DataBase.ViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.buttonRegister
import kotlinx.android.synthetic.main.fragment_register.editTextTextEmailAddress
import kotlinx.android.synthetic.main.fragment_register.editTextTextPassword
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment : Fragment() {
    var navc : NavController?= null
    private lateinit var mUserViewModel : ViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        mUserViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        //navc = Navigation.findNavController(view)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navc = Navigation.findNavController(view)

        buttonRegister.setOnClickListener {
            navc?.navigate(R.id.action_registerFragment2_to_mainFragment)
        }

        buttonRegister.setOnClickListener {

            insertDataToDatabase()
        }

    }

    private fun insertDataToDatabase() {
        val email = editTextTextEmailAddress.text.toString()
        val password = editTextTextPassword.text.toString()

        if(TextUtils.isEmpty(email)){
            editTextTextEmailAddress.error = "Please enter email"
        }else if(!(Patterns.EMAIL_ADDRESS.matcher(email).matches())){
            editTextTextEmailAddress.error = "Invalid email format"
        } else if(TextUtils.isEmpty(password)){
            editTextTextPassword.error = "Please enter password"
        } else if(password.length<4){
            editTextTextPassword.error = "Password is too short"
        } else if(password.length>20){
            editTextTextPassword.error = "Password is too long"
        }
        else {
            if (inputCheck(email, password)) {
                val user = Entity(0, email, password, false)
                mUserViewModel.checkIsValidAccount(user.email,user.password).observe(viewLifecycleOwner, Observer { a ->
                    if(!a) {
                        mUserViewModel.addUser(user)
                        Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG)
                            .show()
                        navc?.navigate(R.id.action_registerFragment2_to_mainFragment)
                        mUserViewModel.update(true, user.email)
                    } else {
                        Toast.makeText(requireContext(),"Такий акаунт уже існує", Toast.LENGTH_LONG).show()
                    }
                })


            } else {
                Toast.makeText(requireContext(), "Fill out all fields", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun inputCheck(email:String, password:String):Boolean{
        return !(TextUtils.isEmpty(email)&&TextUtils.isEmpty(password))
    }


}