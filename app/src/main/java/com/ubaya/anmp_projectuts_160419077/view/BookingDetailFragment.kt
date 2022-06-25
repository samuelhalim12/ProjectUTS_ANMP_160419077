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
import com.ubaya.anmp_projectuts_160419077.viewmodel.BookingDetailViewModel
import com.ubaya.anmp_projectuts_160419077.viewmodel.KostDetailViewModel
import kotlinx.android.synthetic.main.fragment_booking_detail.*
import kotlinx.android.synthetic.main.fragment_kost_detail.*
import kotlinx.android.synthetic.main.kost_booking_item.view.*
import java.text.DecimalFormat
import java.text.NumberFormat

class BookingDetailFragment : Fragment() {
    private lateinit var  viewModel: BookingDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking_detail, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var kostId : Int = 0
        arguments?.let {
            kostId = BookingDetailFragmentArgs.fromBundle(requireArguments()).id
        }
        viewModel = ViewModelProvider(this).get(BookingDetailViewModel::class.java)
        viewModel.fetch(kostId)

        observeViewModel()
        buttonDetailKostDetailBooking.setOnClickListener {
            val action = BookingDetailFragmentDirections.actionFromBookingToKostDetail(viewModel.kostLiveData.value!!.idKost!!)
            Navigation.findNavController(it).navigate(action)
        }
        buttonBeriUlasan.setOnClickListener {
            val action = BookingDetailFragmentDirections.actionBeriUlasanFragment(kostId!!)
            Navigation.findNavController(it).navigate(action)
        }
    }
    private fun observeViewModel() {
        viewModel.kostLiveData.observe(viewLifecycleOwner) {
            val student = viewModel.kostLiveData.value
            student?.let{
                textAlamatDetailBooking.setText(it.alamat)
                textBulanBooking.setText(it.bulan_sewa.toString())
                textTahunBooking.setText(it.tahun_sewa.toString())
                if(it.status_bayar == 1) {
                    textStatusBayarDetailBooking.setText("Lunas")
                } else {
                    textStatusBayarDetailBooking.setText("Belum Lunas")
                }
                val formatter: NumberFormat = DecimalFormat("#,###")
                val myNumber = it.harga
                val formattedNumber: String = formatter.format(myNumber)
                textHargaDetailBooking.setText(formattedNumber.toString())
                textMetodePembayaranDetailBooking.setText(it.metodePembayaran)
                imageDetailBooking.loadImage(it.photoURL, progressLoadImageDetailBooking)
            }
        }
    }
}