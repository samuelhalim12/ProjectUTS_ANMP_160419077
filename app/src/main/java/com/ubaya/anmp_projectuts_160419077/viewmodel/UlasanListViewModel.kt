package com.ubaya.anmp_projectuts_160419077.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.anmp_projectuts_160419077.model.Booking
import com.ubaya.anmp_projectuts_160419077.model.Kost
import com.ubaya.anmp_projectuts_160419077.model.Ulasan

class UlasanListViewModel(application: Application): AndroidViewModel(application) {
    val kostLiveData = MutableLiveData<List<Ulasan>>()
    val kostLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    
    fun refresh(kostId: Int) {
        kostLoadErrorLiveData.value = false
        loadingLiveData.value = true
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.100.3/anmp/projectUTS/ulasan.php?id=$kostId"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Ulasan>>(){}.type
                val result = Gson().fromJson<List<Ulasan>>(it,sType)
                kostLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley",it)
            },
            {
                loadingLiveData.value = false
                kostLoadErrorLiveData.value = true
                Log.d("errorvolley",it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}