package com.ubaya.anmp_projectuts_160419077.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.anmp_projectuts_160419077.R
import com.ubaya.anmp_projectuts_160419077.databinding.MyKostListItemBinding
import com.ubaya.anmp_projectuts_160419077.model.Kost

class MyKostListAdapter (val myKostList: ArrayList<Kost>):RecyclerView
.Adapter<MyKostListAdapter.MyKostViewHolder>(),ButtonEditMyKostListener {
    class MyKostViewHolder(var view:MyKostListItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyKostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.kost_list_item, parent, false)
//        return KostViewHolder(view)
        val v = DataBindingUtil.inflate<MyKostListItemBinding>(inflater,
            R.layout.my_kost_list_item, parent, false)
        return MyKostListAdapter.MyKostViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyKostViewHolder, position: Int) {
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
            mykost = myKostList[position]
            listenerButtonEdit = this@MyKostListAdapter
        }
    }

    override fun getItemCount() = myKostList.size

    fun updateKostList(newKostList: List<Kost>) {
        myKostList.clear()
        myKostList.addAll(newKostList)
        notifyDataSetChanged()
    }

    override fun onButtonEditMyKostListener(v: View) {
        TODO("Not yet implemented")
    }
}