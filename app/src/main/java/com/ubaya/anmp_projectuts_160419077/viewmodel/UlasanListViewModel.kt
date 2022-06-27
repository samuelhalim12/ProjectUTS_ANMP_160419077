package com.ubaya.anmp_projectuts_160419077.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.anmp_projectuts_160419077.model.Booking
import com.ubaya.anmp_projectuts_160419077.model.Kost
import com.ubaya.anmp_projectuts_160419077.model.KostDatabase
import com.ubaya.anmp_projectuts_160419077.model.Ulasan
import com.ubaya.anmp_projectuts_160419077.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UlasanListViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val kostLiveData = MutableLiveData<List<Ulasan>>()
    val kostLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var job = Job()
    private var queue: RequestQueue? = null


    fun refresh(kostId: Int) {
        kostLoadErrorLiveData.value = false
        loadingLiveData.value = true
        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://192.168.100.3/anmp/projectUTS/ulasan.php?id=$kostId"
//        val url = "http://192.168.100.3/anmp/projectUTS/ulasan.php"
        val url = "https://ubaya.fun/native/160419077/uas_anmp/ulasan.php"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Ulasan>>(){}.type
                val result = Gson().fromJson<List<Ulasan>>(it,sType)
                launch {
//                    val db = Room.databaseBuilder(getApplication(),
//                        KostDatabase::class.java, "kostdb").fallbackToDestructiveMigration().build()
                    val db = Room.databaseBuilder(getApplication(),
                        KostDatabase::class.java, "kostdb").build()
//                    db.kostdao().deleteBooking(result)
//                    db.kostdao().deleteAllUlasan()
//                    db.kostdao().insertAllUlasan(result)
                    kostLiveData.value = db.kostdao().selectUlasan(kostId)
                }
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
    fun addUlasan(ulasan:Ulasan) {
        launch {
            val db = buildDB(getApplication())
            db.kostdao().insertUlasan(ulasan)
        }
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}