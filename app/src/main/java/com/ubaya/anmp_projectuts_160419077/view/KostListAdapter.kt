package com.ubaya.anmp_projectuts_160419077.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.anmp_projectuts_160419077.R
import com.ubaya.anmp_projectuts_160419077.model.Kost
import com.ubaya.anmp_projectuts_160419077.util.loadImage
import kotlinx.android.synthetic.main.kost_list_item.view.*
import java.text.DecimalFormat
import java.text.NumberFormat

class KostListAdapter (val kostList: ArrayList<Kost>) : RecyclerView
.Adapter<KostListAdapter.KostViewHolder>() {
    class KostViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.kost_list_item, parent, false)
        return KostViewHolder(view)
    }

    override fun onBindViewHolder(holder: KostViewHolder, position: Int) {
        val kost = kostList[position]
        val kostId = kost.id

        with (holder.view) {
            textAlamat.text = kost.alamat
            textJenisKelamin.text = kost.jenisKelamin
            val formatter: NumberFormat = DecimalFormat("#,###")
            val myNumber = kost.harga
            val formattedNumber: String = formatter.format(myNumber)
            textHarga.setText(formattedNumber.toString())
            buttonDetail.setOnClickListener {
                val action = KostListFragmentDirections.actionKostDetail(kostId)
                Navigation.findNavController(it).navigate(action)
            }
            imageListKost.loadImage(kost.photoURL, progressLoadingListKostPhoto)
        }
    }

    override fun getItemCount() = kostList.size

    fun updateKostList(newKostList: List<Kost>) {
        kostList.clear()
        kostList.addAll(newKostList)
        notifyDataSetChanged()
    }
}