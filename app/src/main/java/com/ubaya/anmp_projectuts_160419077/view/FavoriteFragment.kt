package com.ubaya.anmp_projectuts_160419077.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.anmp_projectuts_160419077.R
import com.ubaya.anmp_projectuts_160419077.viewmodel.FavoriteListViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {
    private lateinit var  viewModel: FavoriteListViewModel
    private val kostListAdapter = FavoriteListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var username : String = "test"
        arguments?.let {
            username = FavoriteFragmentArgs.fromBundle(requireArguments()).username!!
        }
        viewModel = ViewModelProvider(this).get(FavoriteListViewModel::class.java)
        viewModel.refresh(username)

        recViewFavorite.layoutManager = LinearLayoutManager(context)
        recViewFavorite.adapter = kostListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recViewFavorite.visibility = View.GONE
            textErrorFavorite.visibility = View.GONE
            progressLoadFavoriteList.visibility = View.VISIBLE
            viewModel.refresh(username)
            refreshLayout.isRefreshing = false
        }
    }
    private fun observeViewModel() {
        viewModel.kostLiveData.observe(viewLifecycleOwner) {
            kostListAdapter.updateKostList(it)
        }
        viewModel.kostLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorFavorite.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) { //sedang loading
                recViewFavorite.visibility = View.GONE
                progressLoadFavoriteList.visibility = View.VISIBLE
            } else {
                recViewFavorite.visibility = View.VISIBLE
                progressLoadFavoriteList.visibility = View.GONE
            }
        }
    }
}