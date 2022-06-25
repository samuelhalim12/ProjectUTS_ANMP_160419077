package com.ubaya.anmp_projectuts_160419077.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.anmp_projectuts_160419077.R
import com.ubaya.anmp_projectuts_160419077.databinding.FragmentBeriUlasanBinding
import com.ubaya.anmp_projectuts_160419077.model.Ulasan
import com.ubaya.anmp_projectuts_160419077.viewmodel.UlasanListViewModel

class BeriUlasanFragment : Fragment(),ButtonSubmitUlasanClickListener {
    private lateinit var viewModel: UlasanListViewModel
    private lateinit var dataBinding:FragmentBeriUlasanBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_beri_ulasan, container, false)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_beri_ulasan, container, false)
        return dataBinding.root
    }
    var kostId : Int = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            kostId = BeriUlasanFragmentArgs.fromBundle(requireArguments()).idKost
        }

        viewModel = ViewModelProvider(this).get(UlasanListViewModel::class.java)
        dataBinding.ulasan = Ulasan(1,"","sammy",kostId)
        dataBinding.listener = this
    }

    override fun onButtonSubmitUlasanClick(v: View) {
        viewModel.addUlasan(dataBinding.ulasan!!)
        Toast.makeText(
            context,
            dataBinding.ulasan!!.toString(),
            Toast.LENGTH_SHORT
        ).show()
        val action = BeriUlasanFragmentDirections.actionBeriUlasanToUlasanFragment(kostId)
        Navigation.findNavController(v).navigate(action)
    }
}