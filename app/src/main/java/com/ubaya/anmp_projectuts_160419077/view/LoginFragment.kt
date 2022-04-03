package com.ubaya.anmp_projectuts_160419077.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.anmp_projectuts_160419077.R
import com.ubaya.anmp_projectuts_160419077.viewmodel.AccountViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_pre_login.*

class LoginFragment : Fragment() {
    private lateinit var  viewModel: AccountViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        buttonLogin.setOnClickListener {
//            var username : String = editUsername.text.toString()
//            var password : String = editPassword.text.toString()
//            val action = LoginFragmentDirections.actionAccountFragment(username,password)
//            viewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
//            viewModel.fetch(username, password)
//            Navigation.findNavController(it).navigate(action)
//        }
    }
}