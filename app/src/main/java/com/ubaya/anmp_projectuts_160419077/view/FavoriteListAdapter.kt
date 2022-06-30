package com.ubaya.anmp_projectuts_160419077.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.anmp_projectuts_160419077.R
import com.ubaya.anmp_projectuts_160419077.databinding.FavoriteListItemBinding
import com.ubaya.anmp_projectuts_160419077.databinding.KostListItemBinding
import com.ubaya.anmp_projectuts_160419077.model.Favorite
import com.ubaya.anmp_projectuts_160419077.model.Kost
import com.ubaya.anmp_projectuts_160419077.util.loadImage
import com.ubaya.anmp_projectuts_160419077.viewmodel.FavoriteListViewModel
import com.ubaya.anmp_projectuts_160419077.viewmodel.KostDetailViewModel
import kotlinx.android.synthetic.main.favorite_list_item.view.*
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import kotlinx.android.synthetic.main.kost_list_item.view.*
import java.text.DecimalFormat
import java.text.NumberFormat

class FavoriteListAdapter (val kostList: ArrayList<Favorite>) : RecyclerView
.Adapter<FavoriteListAdapter.FavoriteViewHolder>(), ButtonDetailFavoriteListener, ButtonFavoriteListener {
    class FavoriteViewHolder(var view: FavoriteListItemBinding) : RecyclerView.ViewHolder(view.root)
//    private lateinit var  viewModel: KostDetailViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.favorite_list_item, parent, false)
        val v = DataBindingUtil.inflate<FavoriteListItemBinding>(inflater,
            R.layout.favorite_list_item, parent, false)
        return FavoriteViewHolder(v)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        with(holder.view) {
            favorite = kostList[position]
            listener = this@FavoriteListAdapter
            listenerFavorite = this@FavoriteListAdapter
        }
    }

    override fun getItemCount() = kostList.size

    fun updateKostList(newKostList: List<Favorite>) {
        kostList.clear()
        kostList.addAll(newKostList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailFavoriteListener(v: View) {
        val action = FavoriteFragmentDirections.actionFromFavoriteToDetailFragment(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonFavoriteListener(v: View) {
        TODO("Not yet implemented")
    }
}