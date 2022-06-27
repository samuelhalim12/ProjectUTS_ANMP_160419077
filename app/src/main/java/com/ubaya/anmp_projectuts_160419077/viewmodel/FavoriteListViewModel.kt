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
import com.ubaya.anmp_projectuts_160419077.model.Favorite
import com.ubaya.anmp_projectuts_160419077.model.KostDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FavoriteListViewModel(application: Application): AndroidViewModel(application),CoroutineScope {
    val kostLiveData = MutableLiveData<List<Favorite>>()
    val kostLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var job = Job()
    private var queue: RequestQueue? = null
    fun refresh(username: String) {
        kostLoadErrorLiveData.value = false
        loadingLiveData.value = true
        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://192.168.100.3/anmp/projectUTS/favorite.php?username=$username"
//        val url = "http://192.168.100.3/anmp/projectUTS/favorite.php"
        val url = "https://ubaya.fun/native/160419077/uas_anmp/favorite.php"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Favorite>>(){}.type
                val result = Gson().fromJson<List<Favorite>>(it,sType)
                launch {
//                    val db = Room.databaseBuilder(getApplication(),
//                        KostDatabase::class.java, "kostdb").fallbackToDestructiveMigration().build()
                    val db = Room.databaseBuilder(getApplication(),
                        KostDatabase::class.java, "kostdb").build()
//                    db.kostdao().deleteBooking(result)
//                    db.kostdao().deleteAllFavorite()
//                    db.kostdao().insertAllFavorite(result)
                    kostLiveData.value = db.kostdao().selectFavorite(username)
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
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}