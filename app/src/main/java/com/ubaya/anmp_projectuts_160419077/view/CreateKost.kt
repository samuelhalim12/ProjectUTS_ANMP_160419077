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
import com.ubaya.anmp_projectuts_160419077.databinding.FragmentCreateKostBinding
import com.ubaya.anmp_projectuts_160419077.model.Kost
import com.ubaya.anmp_projectuts_160419077.util.NotificationHelper
import com.ubaya.anmp_projectuts_160419077.viewmodel.KostListViewModel

class CreateKost : Fragment(),ButtonCreateClick,RadioJenisKelaminListener {
    private lateinit var viewModel : KostListViewModel
    private lateinit var dataBinding : FragmentCreateKostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_create_kost, container, false)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_kost, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(KostListViewModel::class.java)
//        dataBinding.myrecipe = MyRecipes("",4,0,"")
        dataBinding.mykost = Kost("",0,"",0,"","","","sammy")
        dataBinding.listenerButtonCreate = this
        dataBinding.radioJenisKelaminListener = this
    }

    override fun onButtonCreateClick(v: View) {
        AlertDialog.Builder(context).apply {
            setMessage("Are you sure you want to add Kost?")
            setPositiveButton("Yes") { _, _ ->
                NotificationHelper(v.context).createNotificationKost("Kost Created","A new Kost has been added")
                viewModel.addMyKost(dataBinding.mykost!!)
                Toast.makeText(
                    context,
                    "Your Kost Has Been Inserted",
                    Toast.LENGTH_SHORT
                ).show()
                val action = CreateKostDirections.actionCreateKostToMyKost()
                Navigation.findNavController(v).navigate(action)
            }
            setNegativeButton("Cancel", null)

            create().show()
        }
    }

    override fun onRadioJenisKelaminListener(v: View, jenisKelamin: String, obj: Kost) {
        obj.jenisKelamin = v.tag.toString()
    }
}