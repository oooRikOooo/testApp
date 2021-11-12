package com.example.testapp

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.testapp.DataBase.DataBase
import com.example.testapp.DataBase.Entity
import com.example.testapp.DataBase.Repository
import com.example.testapp.DataBase.ViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.buttonRegister
import kotlinx.android.synthetic.main.fragment_login.editTextTextEmailAddress
import kotlinx.android.synthetic.main.fragment_login.editTextTextPassword
import kotlinx.android.synthetic.main.fragment_register.*


class LoginFragment : Fragment() {

    var navc: NavController? = null
    private lateinit var mUserViewModel: ViewModel





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        mUserViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        mUserViewModel.updateAll(false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navc = Navigation.findNavController(view)
        buttonLogin.setOnClickListener {
            checkUserToLogin()
            //navc?.navigate(R.id.action_loginFragment_to_mainFragment)
        }
        buttonRegister.setOnClickListener {
            navc?.navigate(R.id.action_loginFragment_to_registerFragment2)
        }



    }


    private fun checkUserToLogin(){
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
            login(email,password)
        }


    }

    private fun login(email: String, password: String) {
        val user = Entity(0, email, password,false)
        mUserViewModel.checkIsValidAccount(user.email,user.password).observe(viewLifecycleOwner, Observer { a->
            if(a) {
                mUserViewModel.update(true,email)
                Toast.makeText(requireContext(), "Successfully login", Toast.LENGTH_LONG).show()
                navc?.navigate(R.id.action_loginFragment_to_mainFragment)
            } else {
                //Log.d("Fail",a.toString() )
                Toast.makeText(requireContext(),"Login failed", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun inputCheck(email:String, password:String):Boolean{
        return !(TextUtils.isEmpty(email)&& TextUtils.isEmpty(password))
    }
}
