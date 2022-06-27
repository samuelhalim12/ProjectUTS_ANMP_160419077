package com.ubaya.anmp_projectuts_160419077.view

import android.app.AlertDialog
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
import com.ubaya.anmp_projectuts_160419077.databinding.FragmentEditMyKostBinding
import com.ubaya.anmp_projectuts_160419077.model.Kost
import com.ubaya.anmp_projectuts_160419077.util.NotificationHelper
import com.ubaya.anmp_projectuts_160419077.util.loadImage
import com.ubaya.anmp_projectuts_160419077.viewmodel.KostDetailViewModel
import kotlinx.android.synthetic.main.fragment_edit_my_kost.*

class EditMyKost : Fragment(),ButtonSaveEditKostClick,RadioJenisKelaminListener {
    private lateinit var viewModel : KostDetailViewModel
    private lateinit var dataBinding : FragmentEditMyKostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_edit_my_kost, container, false)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_my_kost, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(KostDetailViewModel::class.java)
        val id = EditMyKostArgs.fromBundle(requireArguments()).id
        viewModel.fetch(id)

//        dataBinding.myrecipe = MyRecipes("",4,0,"")
//        dataBinding.mykost = Kost("",0,"",0,"","","","sammy")
        viewModel.kostLiveData.observe(viewLifecycleOwner) {
            dataBinding.mykost = it
//            dataBinding.jenisKelamin = dataBinding.mykost!!.jenisKelamin.toString()
            imageViewEditMyKost.loadImage(dataBinding.mykost!!.photoURL, progressBarEditMyKostImage)
        }
        dataBinding.listenerButtonSave = this
        dataBinding.radioJenisKelaminListener = this
    }

    override fun onRadioJenisKelaminListener(v: View, jenisKelamin: String, obj: Kost) {
        obj.jenisKelamin = v.tag.toString()
//        dataBinding.jenisKelamin = v.tag.toString()
    }

    override fun onButtonSaveEditKostClick(v: View) {
        viewModel.editMyKost(dataBinding.mykost!!)
        Toast.makeText(
            context,
            "My Kost Updated",
            Toast.LENGTH_SHORT
        ).show()
        val action = EditMyKostDirections.actionEditMyKostToMyKost()
        Navigation.findNavController(v).navigate(action)
    }
}