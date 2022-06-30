package com.ubaya.anmp_projectuts_160419077.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.anmp_projectuts_160419077.R
import com.ubaya.anmp_projectuts_160419077.databinding.KostListItemBinding
import com.ubaya.anmp_projectuts_160419077.model.Favorite
import com.ubaya.anmp_projectuts_160419077.model.Kost
import com.ubaya.anmp_projectuts_160419077.util.loadImage
import com.ubaya.anmp_projectuts_160419077.viewmodel.FavoriteListViewModel
import kotlinx.android.synthetic.main.kost_list_item.view.*
import java.text.DecimalFormat
import java.text.NumberFormat

class KostListAdapter (val kostList: ArrayList<Kost>) : RecyclerView
.Adapter<KostListAdapter.KostViewHolder>(), ButtonAddFavoriteListener, ButtonDetailListener {
    class KostViewHolder(var view: KostListItemBinding) : RecyclerView.ViewHolder(view.root)
    private lateinit var  viewModel: FavoriteListViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.kost_list_item, parent, false)
//        return KostViewHolder(view)
        val v = DataBindingUtil.inflate<KostListItemBinding>(inflater,
            R.layout.kost_list_item, parent, false)
        return KostViewHolder(v)
    }

    override fun onBindViewHolder(holder: KostViewHolder, position: Int) {
//        val kost = kostList[position]
//        val kostId = kost.id

        viewModel = ViewModelProvider(this).get(FavoriteListViewModel::class.java)
        with (holder.view) {
//            textAlamat.text = kost.alamat
//            textJenisKelamin.text = kost.jenisKelamin
//            val formatter: NumberFormat = DecimalFormat("#,###")
//            val myNumber = kost.harga
//            val formattedNumber: String = formatter.format(myNumber)
//            textHarga.setText(formattedNumber.toString())
//            buttonDetail.setOnClickListener {
//                val action = KostListFragmentDirections.actionKostDetail(kostId)
//                Navigation.findNavController(it).navigate(action)
//            }
//            imageListKost.loadImage(kost.photoURL, progressLoadingListKostPhoto)
            kost = kostList[position]
            listener = this@KostListAdapter
            listenerButtonAddFavorite = this@KostListAdapter
        }
    }

    override fun getItemCount() = kostList.size

    fun updateKostList(newKostList: List<Kost>) {
        kostList.clear()
        kostList.addAll(newKostList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailListener(v: View) {
        val action = KostListFragmentDirections.actionKostDetail(v.tag.toString().toInt())
                Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonAddFavoriteListener(v: View) {
        TODO("Not yet implemented")
    }
}