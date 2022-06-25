package com.ubaya.anmp_projectuts_160419077.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.anmp_projectuts_160419077.R
import com.ubaya.anmp_projectuts_160419077.util.loadImage
import com.ubaya.anmp_projectuts_160419077.viewmodel.AccountViewModel
import com.ubaya.anmp_projectuts_160419077.viewmodel.KostDetailViewModel
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_kost_detail.*

class EditProfileFragment : Fragment() {
    private lateinit var  viewModel: AccountViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var username : String = "test"
        var password : String = "test"
        arguments?.let {
            username = EditProfileFragmentArgs.fromBundle(requireArguments()).username
            password = EditProfileFragmentArgs.fromBundle(requireArguments()).password
        }
        viewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        viewModel.fetch(username,password)

        observeViewModel()
    }
    private fun observeViewModel() {
        viewModel.accountsLiveData.observe(viewLifecycleOwner) {
            val student = viewModel.accountsLiveData.value
            student?.let{
                editUsername.setText(it.username)
                editPassword.setText(it.password)
                editPhone.setText(it.phone)
                imageEditProfilePicture.loadImage(it.photoURL,progressLoadEditProfilePicture)
            }
        }
    }
}