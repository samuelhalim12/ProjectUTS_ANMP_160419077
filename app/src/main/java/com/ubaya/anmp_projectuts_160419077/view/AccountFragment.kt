package com.ubaya.anmp_projectuts_160419077.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.anmp_projectuts_160419077.R
import com.ubaya.anmp_projectuts_160419077.util.loadImage
import com.ubaya.anmp_projectuts_160419077.viewmodel.AccountViewModel
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_booking_detail.*
import java.text.DecimalFormat
import java.text.NumberFormat

class AccountFragment : Fragment() {

    private lateinit var  viewModel: AccountViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var username : String = "sammy"
        var password : String = "1234"

        viewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        viewModel.fetch(username, password)
        observeViewModel()
        buttonFavorite.setOnClickListener {
            val action = AccountFragmentDirections.actionFavoriteFragment(username)
            Navigation.findNavController(it).navigate(action)
        }
        buttonEditProfile.setOnClickListener {
            val action = AccountFragmentDirections.actionEditProfile(username,password)
            Navigation.findNavController(it).navigate(action)
        }
    }
    private fun observeViewModel() {
        viewModel.accountsLiveData.observe(viewLifecycleOwner) {
            val student = viewModel.accountsLiveData.value
            student?.let{
                textPhoneAccount.setText(it.phone.toString())
                textUsernameAccount.setText(it.username.toString())
                imageProfilePicture.loadImage(it.photoURL, progressProfilePicture)
            }
        }
    }
}