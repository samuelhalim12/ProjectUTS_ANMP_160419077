package com.ubaya.anmp_projectuts_160419077.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.anmp_projectuts_160419077.R
import com.ubaya.anmp_projectuts_160419077.model.KostDatabase
import java.lang.Exception

val DB_NAME = "kostdb"

fun buildDB(context: Context):KostDatabase {
    val db = Room.databaseBuilder(context,
        KostDatabase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5)
        .build()
    return db
}

val MIGRATION_1_2 = object: Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE kost (id INTEGER PRIMARY KEY,alamat TEXT NOT NULL,kamarTersedia INTEGER NOT NULL,deskripsi TEXT NOT NULL, harga INTEGER NOT NULL, jenisKelamin TEXT NOT NULL, phone TEXT NOT NULL, photoURL TEXT NOT NULL)")
        database.execSQL("CREATE TABLE account (username TEXT PRIMARY KEY,password TEXT NOT NULL, phone TEXT NOT NULL, photoURL TEXT NOT NULL)")
        database.execSQL("CREATE TABLE booking (id INTEGER PRIMARY KEY,status_bayar INTEGER,bulan_sewa INTEGER NOT NULL, tahun_sewa INTEGER NOT NULL, metodePembayaran TEXT NOT NULL, idKost INTEGER NOT NULL)")
        database.execSQL("CREATE TABLE ulasan (id INTEGER PRIMARY KEY,rating INTEGER,deskripsi TEXT, username TEXT NOT NULL, kostId INTEGER NOT NULL)")
        database.execSQL("CREATE TABLE favorite (id INTEGER PRIMARY KEY, username TEXT NOT NULL, idKost INTEGER NOT NULL)")
    }
}
val MIGRATION_2_3 = object: Migration(2,3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE booking ADD COLUMN alamat TEXT NOT NULL")
        database.execSQL("ALTER TABLE booking ADD COLUMN harga INTEGER NOT NULL")
        database.execSQL("ALTER TABLE booking ADD COLUMN photoURL TEXT NOT NULL")
//        database.execSQL("ALTER TABLE ulasan RENAME COLUMN idKost TO kostId")
//        database.execSQL("ALTER TABLE kost ADD COLUMN username TEXT NOT NULL")

    }
}
val MIGRATION_3_4 = object: Migration(3,4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE ulasan RENAME COLUMN idKost TO kostId")
    }
}
val MIGRATION_4_5 = object: Migration(4,5) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE kost ADD COLUMN username TEXT NOT NULL")
    }
}
val MIGRATION_2_1 = object: Migration(2,1) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE kost ADD COLUMN username TEXT NOT NULL")
    }
}


    fun ImageView.loadImage(url:String?, progressBar: ProgressBar) {
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                progressBar.visibility = View.VISIBLE
            }

        })
}
//@BindingAdapter("loadWithPicasso")
//fun loadWithPicasso(imageView2: ImageView, url: String? ) {
//    Picasso.get()
//        .load(url)
//        .resize(400,400)
//        .centerCrop()
//        .error(R.drawable.ic_baseline_error_24)
//        .into(imageView2, object: Callback {
//            override fun onSuccess() {
////                progressBar.visibility = View.GONE
//            }
//
//            override fun onError(e: Exception?) {
//
//            }
//
//        })
////    Picasso.get().load(imageUrl).into(imageView2)
//}
@BindingAdapter("android:imageUrl","android:progressBar")
fun loadPhotoUrl(v:ImageView, url:String, pb:ProgressBar) {
    v.loadImage(url,pb)
}
