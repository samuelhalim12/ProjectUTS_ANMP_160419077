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
import com.ubaya.anmp_projectuts_160419077.model.Kost
import com.ubaya.anmp_projectuts_160419077.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class KostDetailViewModel(application: Application): AndroidViewModel(application),CoroutineScope {
    val kostLiveData = MutableLiveData<Kost>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    private var job = Job()

    fun fetch(kostId: Int) {
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://192.168.100.3/anmp/projectUTS/kost.php?id=$kostId"
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object : TypeToken<Kost>(){}.type
//                val result = Gson().fromJson<Kost>(it,sType)
//                kostLiveData.value = result
//                Log.d("showvolley",it)
//            },
//            {
//                Log.d("errorvolley",it.toString())
//            }
//        ).apply {
//            tag = "TAG"
//        }
//        queue?.add(stringRequest)
        launch {
            val db = buildDB(getApplication())
            kostLiveData.value =  db.kostdao().selectKost(kostId)
        }
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}