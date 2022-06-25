package com.ubaya.anmp_projectuts_160419077.model

import androidx.room.*

@Dao
interface KostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertAll(vararg kost:Kost)

    @Query("Select * From kost")
    suspend fun selectAllKost() : List<Kost>

    @Query("Select * from kost where id = :id")
    suspend fun selectKost(id:Int):Kost

    @Delete
    suspend fun deleteKost(kost:Kost)

    @Query("UPDATE kost SET alamat=:alamat, kamarTersedia=:kamarTersedia, deskripsi=:deskripsi, harga=:harga," +
            "jenisKelamin=:jenisKelamin, phone=:phone, photoURL=:photoURL WHERE id = :id")
    suspend fun update(title:String, notes:String, priority:Int, id:Int)

    @Query("UPDATE todo SET is_done=1 WHERE uuid = :id")
    suspend fun taskChecked(id:Int)

}