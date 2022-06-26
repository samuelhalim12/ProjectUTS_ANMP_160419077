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
import com.ubaya.anmp_projectuts_160419077.viewmodel.KostListViewModel
import kotlinx.android.synthetic.main.fragment_booking.*
import kotlinx.android.synthetic.main.fragment_kost_list.*
import kotlinx.android.synthetic.main.fragment_kost_list.refreshLayout

class BookingFragment : Fragment() {
    private lateinit var  viewModel: BookingListViewModel
    private val kostListAdapter = BookingListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookingListViewModel::class.java)
        viewModel.fetch()

        recViewBooking.layoutManager = LinearLayoutManager(context)
        recViewBooking.adapter = kostListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recViewBooking.visibility = View.GONE
            textErrorBooking.visibility = View.GONE
            progressBarImageBooking.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }
    private fun observeViewModel() {
        viewModel.kostLiveData.observe(viewLifecycleOwner) {
            kostListAdapter.updateKostList(it)
        }
        viewModel.kostLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorBooking.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) { //sedang loading
                recViewBooking.visibility = View.GONE
                progressBarImageBooking.visibility = View.VISIBLE
            } else {
                recViewBooking.visibility = View.VISIBLE
                progressBarImageBooking.visibility = View.GONE
            }
        }
    }
}