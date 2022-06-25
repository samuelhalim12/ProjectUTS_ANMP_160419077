package com.ubaya.anmp_projectuts_160419077.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.anmp_projectuts_160419077.R
import com.ubaya.anmp_projectuts_160419077.model.Kost
import com.ubaya.anmp_projectuts_160419077.model.Ulasan
import com.ubaya.anmp_projectuts_160419077.util.loadImage
import kotlinx.android.synthetic.main.kost_list_item.view.*
import kotlinx.android.synthetic.main.ulasan_list_item.view.*
import java.text.DecimalFormat
import java.text.NumberFormat

class UlasanListAdapter (val kostList: ArrayList<Ulasan>) : RecyclerView
.Adapter<UlasanListAdapter.UlasanViewHolder>() {
    class UlasanViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UlasanViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.ulasan_list_item, parent, false)
        return UlasanViewHolder(view)
    }

    override fun onBindViewHolder(holder: UlasanViewHolder, position: Int) {
        val kost = kostList[position]
        val kostId = kost.id
        with (holder.view) {
            textRating.text = kost.rating.toString()
            textDescriptionUlasan.text = kost.deskripsi
            textUsername.text = kost.username
        }
    }

    override fun getItemCount() = kostList.size

    fun updateKostList(newKostList: List<Ulasan>) {
        kostList.clear()
        kostList.addAll(newKostList)
        notifyDataSetChanged()
    }
}