package com.ubaya.anmp_projectuts_160419077.model
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.anmp_projectuts_160419077.util.MIGRATION_1_2
import com.ubaya.anmp_projectuts_160419077.util.MIGRATION_2_3
import com.ubaya.anmp_projectuts_160419077.util.MIGRATION_3_4

@Database(entities = arrayOf(Kost::class, Account::class, Booking::class, Ulasan::class, Favorite::class), version = 2)
abstract class KostDatabase:RoomDatabase() {
    abstract fun kostdao():KostDao
    companion object {
        @Volatile private var instance: KostDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context:Context) =
            Room.databaseBuilder(
                context.applicationContext,
                KostDatabase::class.java,
                "kostdb")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                .build()
        operator fun invoke(context:Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance?: buildDatabase(context).also {
                        instance=it
                    }
                }
            }
        }
    }
}