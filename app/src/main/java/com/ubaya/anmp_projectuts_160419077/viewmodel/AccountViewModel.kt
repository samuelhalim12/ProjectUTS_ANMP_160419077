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
import com.ubaya.anmp_projectuts_160419077.model.Account
import com.ubaya.anmp_projectuts_160419077.model.KostDatabase
import com.ubaya.anmp_projectuts_160419077.model.Ulasan
import com.ubaya.anmp_projectuts_160419077.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AccountViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val accountsLiveData = MutableLiveData<Account>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    private var job = Job()
    fun fetch(username: String, password: String) {

        queue = Volley.newRequestQueue(getApplication())

//        val url = "http://192.168.100.3/anmp/projectUTS/account.php?username=$username&password=$password"
//        val url = "http://192.168.100.3/anmp/projectUTS/account.php"
        val url = "https://ubaya.fun/native/160419077/uas_anmp/account.php"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
//                Log.d("username", username)
//                Log.d("password", password)

                val sType = object : TypeToken<List<Account>>(){}.type
                val result = Gson().fromJson<List<Account>>(it,sType)
                launch {
//                    val db = Room.databaseBuilder(getApplication(),
//                        KostDatabase::class.java, "kostdb").fallbackToDestructiveMigration().build()
                    val db = Room.databaseBuilder(getApplication(),
                        KostDatabase::class.java, "kostdb").build()
//                    db.kostdao().deleteBooking(result)
//                    db.kostdao().deleteAllAccount()
                    db.kostdao().insertAllAccount(result)
                    accountsLiveData.value = db.kostdao().selectAccount(username,password)
                }
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
    fun editProfile(account: Account) {
        launch {
            val db = buildDB(getApplication())
            db.kostdao().updateAccount(account.password!!, account.phone!!, account.photoURL!!, account.username)
        }
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}