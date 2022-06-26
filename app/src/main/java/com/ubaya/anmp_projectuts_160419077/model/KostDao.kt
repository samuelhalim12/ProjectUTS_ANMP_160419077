package com.ubaya.anmp_projectuts_160419077.model

import androidx.room.*

@Dao
interface KostDao {
    // Kost
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertAllKost(kost:List<Kost>)

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertKost(kost:Kost)

    @Query("Select * From kost")
    suspend fun selectAllKost() : List<Kost>

    @Query("Select * from kost where id = :id")
    suspend fun selectKost(id:Int):Kost

    @Query("Select * from kost where username = :username")
    suspend fun selectKostCertainUser(username:String):List<Kost>

    @Delete
    suspend fun deleteKost(kost:Kost)

    @Query("DELETE FROM kost")
    suspend fun deleteAllKost()

    @Query("UPDATE kost SET alamat=:alamat, kamarTersedia=:kamarTersedia, deskripsi=:deskripsi, harga=:harga," +
            "jenisKelamin=:jenisKelamin, phone=:phone, photoURL=:photoURL WHERE id = :id")
    suspend fun updateKost(alamat:String, kamarTersedia:Int, deskripsi:String,
                       harga:Int,jenisKelamin:String,phone:String,photoURL:String,id:Int)

    //Booking
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertAllBooking(booking:List<Booking>)

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertBooking(booking:Booking)

    @Query("Select * From booking")
    suspend fun selectAllBooking() : List<Booking>

    @Query("Select * from booking where id = :id")
    suspend fun selectBooking(id:Int):Booking

    @Delete
    suspend fun deleteBooking(booking:List<Booking>)

    @Query("DELETE FROM booking")
    suspend fun deleteAllBooking()

    //Favorite
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertAllFavorite(favorite:List<Favorite>)

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertFavorite(favorite:Favorite)

    @Query("Select * From favorite")
    suspend fun selectAllFavorite() : List<Favorite>

    @Query("Select * from favorite where username = :username")
    suspend fun selectFavorite(username:String): List<Favorite>

    @Delete
    suspend fun deleteFavorite(favorite:List<Favorite>)

    @Query("DELETE FROM favorite")
    suspend fun deleteAllFavorite()

    //Ulasan
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertAllUlasan(ulasan:List<Ulasan>)

    @Insert
    suspend fun insertUlasan(ulasan:Ulasan)

    @Query("Select * From ulasan")
    suspend fun selectAllUlasan() : List<Ulasan>

    @Query("Select * from ulasan where kostId = :id")
    suspend fun selectUlasan(id:Int):List<Ulasan>

    @Delete
    suspend fun deleteUlasan(ulasan:List<Ulasan>)

    @Query("DELETE FROM ulasan")
    suspend fun deleteAllUlasan()

    //Account
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertAllAccount(account:List<Account>)

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertAccount(account:Account)

    @Query("Select * From account")
    suspend fun selectAllAccount() : List<Account>

    @Query("Select * from account where username = :username and password = :password")
    suspend fun selectAccount(username:String, password:String):Account

    @Delete
    suspend fun deleteAccount(account:List<Account>)

    @Query("DELETE FROM account")
    suspend fun deleteAllAccount()

    @Query("UPDATE account SET password=:password, phone=:phone, photoURL=:photoURL WHERE username = :username")
    suspend fun updateAccount(password:String,phone:String,photoURL:String,username:String)
}