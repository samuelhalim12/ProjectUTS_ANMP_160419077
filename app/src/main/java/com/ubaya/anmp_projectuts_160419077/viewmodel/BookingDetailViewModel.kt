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
import com.ubaya.anmp_projectuts_160419077.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BookingDetailViewModel(application: Application
): AndroidViewModel(application), CoroutineScope {
    val kostLiveData = MutableLiveData<Booking>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    private var job = Job()

    fun fetch(kostId: Int) {
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://192.168.100.3/anmp/projectUTS/booking.php?id=$kostId"
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object : TypeToken<Booking>(){}.type
//                val result = Gson().fromJson<Booking>(it,sType)
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
            kostLiveData.value =  db.kostdao().selectBooking(kostId)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

}