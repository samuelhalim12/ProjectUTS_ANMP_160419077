package com.ubaya.anmp_projectuts_160419077.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.anmp_projectuts_160419077.R
import com.ubaya.anmp_projectuts_160419077.databinding.FragmentEditProfileBinding
import com.ubaya.anmp_projectuts_160419077.databinding.FragmentMyKostBinding
import com.ubaya.anmp_projectuts_160419077.viewmodel.KostListViewModel
import kotlinx.android.synthetic.main.fragment_my_kost.*

class MyKostFragment : Fragment() , ButtonAddMyKostClick{
    private lateinit var  viewModel: KostListViewModel
    private val myKostListAdapter = MyKostListAdapter(arrayListOf())
    private lateinit var dataBinding: FragmentMyKostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_my_kost, container, false)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_kost, container, false)
        return dataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(KostListViewModel::class.java)
        viewModel.fetchMyKost("sammy")

        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = myKostListAdapter
        dataBinding.listenerAddMyKost = this
        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            textErrorMyKost.visibility = View.GONE
            progressMyKostList.visibility = View.VISIBLE
//            viewModel.refresh()
            viewModel.fetchMyKost("sammy")
            refreshLayout.isRefreshing = false
        }
    }
    private fun observeViewModel() {
        viewModel.myKostLiveData.observe(viewLifecycleOwner) {
            myKostListAdapter.updateKostList(it)
        }
        viewModel.kostLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorMyKost.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) { //sedang loading
                recView.visibility = View.GONE
                progressMyKostList.visibility = View.VISIBLE
            } else {
                recView.visibility = View.VISIBLE
                progressMyKostList.visibility = View.GONE
            }
        }
    }

    override fun onButtonAddMyKostClick(v: View) {
        val action = MyKostFragmentDirections.actionMyKostToCreateKost()
        Navigation.findNavController(v).navigate(action)
    }
}