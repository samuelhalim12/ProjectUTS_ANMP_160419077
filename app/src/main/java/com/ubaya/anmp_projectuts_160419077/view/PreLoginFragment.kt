package com.ubaya.anmp_projectuts_160419077.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ubaya.anmp_projectuts_160419077.R
import kotlinx.android.synthetic.main.fragment_pre_login.*

class PreLoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pre_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        buttonSignIn.setOnClickListener {
//            val action = PreLoginFragmentDirections.actionLoginFragment()
//            // Jalankan action tersebut
//            Navigation.findNavController(it).navigate(action)
//        }
//        buttonSignUp.setOnClickListener {
//            val action = PreLoginFragmentDirections.actionRegisterFragment()
//            // Jalankan action tersebut
//            Navigation.findNavController(it).navigate(action)
//        }
    }
}