package com.ubaya.anmp_projectuts_160419077.model

import androidx.room.*

@Dao
interface KostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertAllKost(kost:List<Kost>)

    @Query("Select * From kost")
    suspend fun selectAllKost() : List<Kost>

    @Query("Select * from kost where id = :id")
    suspend fun selectKost(id:Int):Kost

    @Delete
    suspend fun deleteKost(kost:Kost)

    @Query("DELETE FROM kost")
    suspend fun deleteAllKost()

    @Query("UPDATE kost SET alamat=:alamat, kamarTersedia=:kamarTersedia, deskripsi=:deskripsi, harga=:harga," +
            "jenisKelamin=:jenisKelamin, phone=:phone, photoURL=:photoURL WHERE id = :id")
    suspend fun updateKost(alamat:String, kamarTersedia:Int, deskripsi:String,
                       harga:Int,jenisKelamin:String,phone:String,photoURL:String,id:Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertAllBooking(booking:List<Booking>)

    @Query("Select * From booking")
    suspend fun selectAllBooking() : List<Booking>

    @Query("Select * from booking where id = :id")
    suspend fun selectBooking(id:Int):Booking

    @Delete
    suspend fun deleteBooking(booking:List<Booking>)

    @Query("DELETE FROM booking")
    suspend fun deleteAllBooking()
}