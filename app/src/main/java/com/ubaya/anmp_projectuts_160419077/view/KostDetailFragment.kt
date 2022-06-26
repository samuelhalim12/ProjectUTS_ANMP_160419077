package com.ubaya.anmp_projectuts_160419077.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.anmp_projectuts_160419077.R
import com.ubaya.anmp_projectuts_160419077.databinding.FragmentEditProfileBinding
import com.ubaya.anmp_projectuts_160419077.databinding.FragmentKostDetailBinding
import com.ubaya.anmp_projectuts_160419077.model.Booking
import com.ubaya.anmp_projectuts_160419077.util.loadImage
import com.ubaya.anmp_projectuts_160419077.viewmodel.BookingDetailViewModel
import com.ubaya.anmp_projectuts_160419077.viewmodel.KostDetailViewModel
import kotlinx.android.synthetic.main.fragment_kost_detail.*

class KostDetailFragment : Fragment(),ButtonUlasanClickListener,ButtonSewaClickListener,RadioStatusBayarListener {
    private lateinit var  viewModel: KostDetailViewModel
    private lateinit var  viewModel2: BookingDetailViewModel
    private lateinit var dataBinding: FragmentKostDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_kost_detail, container, false)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_kost_detail, container, false)
        return dataBinding.root
    }
    var kostId : Int = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            kostId = KostDetailFragmentArgs.fromBundle(requireArguments()).kostId
        }
        viewModel = ViewModelProvider(this).get(KostDetailViewModel::class.java)
        viewModel2 = ViewModelProvider(this).get(BookingDetailViewModel::class.java)
        viewModel.fetch(kostId)
        dataBinding.listenerButtonSewa = this
        dataBinding.listenerButtonUlasan = this
        dataBinding.radioStatusBayarListener = this
        dataBinding.belumlunas = 0
        dataBinding.lunas = 1

        observeViewModel()
//        buttonUlasan.setOnClickListener {
//            val action = KostDetailFragmentDirections.actionUlasanFragment(kostId)
//            Navigation.findNavController(it).navigate(action)
//        }
    }
    private fun observeViewModel() {
        viewModel.kostLiveData.observe(viewLifecycleOwner) {
//            val student = viewModel.kostLiveData.value
//            student?.let{
//                imageKostDetail.loadImage(it.photoURL, progressBarKostDetail)
//                textAlamatDetail.setText(it.alamat)
//                textPhone.setText(it.phone)
//                textDescriptionDetail.setText(it.deskripsi)
//                textJenisKelaminDetail.setText(it.jenisKelamin)
//                textKamarTersedia.setText(it.kamarTersedia.toString())
//            }
            dataBinding.detailKost = viewModel.kostLiveData.value
            imageKostDetail.loadImage(dataBinding.detailKost!!.photoURL, progressBarKostDetail)
        }
        viewModel2.kostLiveData.observe(viewLifecycleOwner) {
            dataBinding.booking = Booking(dataBinding.detailKost!!.alamat,0,0,0,
                0,"",dataBinding.detailKost!!.photoURL,dataBinding.detailKost!!.id)
        }
    }

    override fun onButtonUlasanClickListener(v: View) {
        val action = KostDetailFragmentDirections.actionUlasanFragment(kostId)
        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonSewaClickListener(v: View) {
        viewModel2.insertBooking(dataBinding.booking!!)
        val action = KostDetailFragmentDirections.actionKostDetailToItemBooking()
        Navigation.findNavController(v).navigate(action)
    }

    override fun onRadioStatusBayarListener(v: View, status_bayar: Int, obj: Booking) {
        obj.status_bayar = v.tag as Int
    }
}