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
import com.ubaya.anmp_projectuts_160419077.model.Account

class AccountViewModel(application: Application):AndroidViewModel(application) {
    val accountsLiveData = MutableLiveData<Account>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun fetch(username: String?, password: String?) {

        queue = Volley.newRequestQueue(getApplication())

        val url = "http://192.168.100.3/anmp/projectUTS/account.php?username=$username&password=$password"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
//                Log.d("username", username)
//                Log.d("password", password)

                val sType = object : TypeToken<Account>(){}.type
                val result = Gson().fromJson<Account>(it,sType)
                accountsLiveData.value = result
                Log.d("showvolley",it)
            },
            {
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