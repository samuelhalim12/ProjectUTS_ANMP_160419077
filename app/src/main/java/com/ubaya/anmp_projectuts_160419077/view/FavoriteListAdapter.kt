package com.ubaya.anmp_projectuts_160419077.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.anmp_projectuts_160419077.R
import com.ubaya.anmp_projectuts_160419077.model.Favorite
import com.ubaya.anmp_projectuts_160419077.util.loadImage
import kotlinx.android.synthetic.main.favorite_list_item.view.*
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import kotlinx.android.synthetic.main.kost_list_item.view.*
import java.text.DecimalFormat
import java.text.NumberFormat

class FavoriteListAdapter (val kostList: ArrayList<Favorite>) : RecyclerView
.Adapter<FavoriteListAdapter.FavoriteViewHolder>() {
    class FavoriteViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.favorite_list_item, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val kost = kostList[position]
        val kostId = kost.idKost
        with(holder.view) {
            textAlamatFavorite.text = kost.alamat
            textJenisKelaminFavorite.text = kost.jenisKelamin
            val formatter: NumberFormat = DecimalFormat("#,###")
            val myNumber = kost.harga
            val formattedNumber: String = formatter.format(myNumber)
            textHargaFavorite.setText(formattedNumber.toString())
            buttonDetailFavorite.setOnClickListener {
                val action = FavoriteFragmentDirections.actionFromFavoriteToDetailFragment(kost.idKost)
                Navigation.findNavController(it).navigate(action)
            }
            imageFavoriteKost.loadImage(kost.photoURL, progressPhotoFavorite)
        }
    }

    override fun getItemCount() = kostList.size

    fun updateKostList(newKostList: ArrayList<Favorite>) {
        kostList.clear()
        kostList.addAll(newKostList)
        notifyDataSetChanged()
    }
}