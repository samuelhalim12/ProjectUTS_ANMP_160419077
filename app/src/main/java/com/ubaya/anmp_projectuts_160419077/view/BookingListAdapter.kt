package com.ubaya.anmp_projectuts_160419077.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.anmp_projectuts_160419077.R
import com.ubaya.anmp_projectuts_160419077.databinding.KostBookingItemBinding
import com.ubaya.anmp_projectuts_160419077.databinding.KostListItemBinding
import com.ubaya.anmp_projectuts_160419077.model.Booking
import com.ubaya.anmp_projectuts_160419077.util.loadImage
import com.ubaya.anmp_projectuts_160419077.viewmodel.FavoriteListViewModel
import kotlinx.android.synthetic.main.kost_booking_item.view.*
import java.text.DecimalFormat
import java.text.NumberFormat

class BookingListAdapter (val bookingList: ArrayList<Booking>) : RecyclerView
.Adapter<BookingListAdapter.BookingViewHolder>(), ButtonDetailBookingListener {
    class BookingViewHolder(var view: KostBookingItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.kost_booking_item, parent, false)
//        return BookingViewHolder(view)
        val v = DataBindingUtil.inflate<KostBookingItemBinding>(inflater,
            R.layout.kost_booking_item, parent, false)
        return BookingListAdapter.BookingViewHolder(v)
    }

    override fun onBindViewHolder(holder:BookingViewHolder, position: Int) {
//        val kost = bookingList[position]
//        val bookingId = kost.id
        with (holder.view) {

//            if(kost.status_bayar.toString() == "1") {
//                textStatusBayar.text = "Lunas"
//            } else {
//                textStatusBayar.text = "Belum Lunas"
//            }
//            val formatter: NumberFormat = DecimalFormat("#,###")
//            val myNumber = kost.harga
//            val formattedNumber: String = formatter.format(myNumber)
//            textAlamatBooking.text = kost.alamat
//            textHargaBooking.setText(formattedNumber.toString())
//            buttonDetailBooking.setOnClickListener {
//                val action = BookingFragmentDirections.actionBookingDetailFragment(bookingId)
//                Navigation.findNavController(it).navigate(action)
//            }
            booking = bookingList[position]
            listener = this@BookingListAdapter
//            imageBookingKost.loadImage(kost.photoURL, progressImageBookingList)
        }
    }

    override fun getItemCount() = bookingList.size

    fun updateKostList(newKostList: List<Booking>) {
        bookingList.clear()
        bookingList.addAll(newKostList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailBookingListener(v: View) {
        val action = BookingFragmentDirections.actionBookingDetailFragment(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }
}