package com.ubaya.anmp_projectuts_160419077.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.anmp_projectuts_160419077.R
import com.ubaya.anmp_projectuts_160419077.viewmodel.BookingListViewModel
import com.ubaya.anmp_projectuts_160419077.viewmodel.UlasanListViewModel
import kotlinx.android.synthetic.main.fragment_ulasan.*
import kotlinx.android.synthetic.main.fragment_ulasan.refreshLayout
import kotlinx.android.synthetic.main.fragment_kost_list.*
import kotlinx.android.synthetic.main.fragment_ulasan.*

class UlasanFragment : Fragment() {
    private lateinit var  viewModel: UlasanListViewModel
    private val kostListAdapter = UlasanListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ulasan, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var kostId : Int = 0
        arguments?.let {
            kostId = UlasanFragmentArgs.fromBundle(requireArguments()).kostId
        }
        viewModel = ViewModelProvider(this).get(UlasanListViewModel::class.java)
        viewModel.refresh(kostId)

        recViewUlasan.layoutManager = LinearLayoutManager(context)
        recViewUlasan.adapter = kostListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recViewUlasan.visibility = View.GONE
            textErrorUlasan.visibility = View.GONE
            progressLoadUlasan.visibility = View.VISIBLE
            viewModel.refresh(kostId)
            refreshLayout.isRefreshing = false
        }
    }
    private fun observeViewModel() {
        viewModel.kostLiveData.observe(viewLifecycleOwner) {
            kostListAdapter.updateKostList(it)
        }
        viewModel.kostLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorUlasan.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) { //sedang loading
                recViewUlasan.visibility = View.GONE
                progressLoadUlasan.visibility = View.VISIBLE
            } else {
                recViewUlasan.visibility = View.VISIBLE
                progressLoadUlasan.visibility = View.GONE
            }
        }
    }
}