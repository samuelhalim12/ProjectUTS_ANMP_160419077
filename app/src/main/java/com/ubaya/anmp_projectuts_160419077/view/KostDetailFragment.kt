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
import com.ubaya.anmp_projectuts_160419077.viewmodel.KostDetailViewModel
import kotlinx.android.synthetic.main.fragment_kost_detail.*

class KostDetailFragment : Fragment() {
    private lateinit var  viewModel: KostDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kost_detail, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var kostId : String? = "test"
        arguments?.let {
            kostId = KostDetailFragmentArgs.fromBundle(requireArguments()).kostId
        }
        viewModel = ViewModelProvider(this).get(KostDetailViewModel::class.java)
        viewModel.fetch(kostId)

        observeViewModel()
        buttonUlasan.setOnClickListener {
            val action = KostDetailFragmentDirections.actionUlasanFragment(kostId)
            Navigation.findNavController(it).navigate(action)
        }
    }
    private fun observeViewModel() {
        viewModel.kostLiveData.observe(viewLifecycleOwner) {
            val student = viewModel.kostLiveData.value
            student?.let{
                imageKostDetail.loadImage(it.photoURL, progressBarKostDetail)
                textAlamatDetail.setText(it.alamat)
                textPhone.setText(it.phone)
                textDescriptionDetail.setText(it.deskripsi)
                textJenisKelaminDetail.setText(it.jenisKelamin)
                textKamarTersedia.setText(it.kamarTersedia.toString())
            }
        }
    }
}