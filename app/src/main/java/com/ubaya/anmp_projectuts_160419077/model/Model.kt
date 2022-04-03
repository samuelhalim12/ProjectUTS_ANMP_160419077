package com.ubaya.anmp_projectuts_160419077.model
import com.google.gson.annotations.SerializedName
data class Kost (
    val id:String?,
    var alamat: String?,
    var kamarTersedia: Int?,
    var deskripsi: String?,
    var harga: Int?,
    var jenisKelamin: String?,
    var phone: String?,
    var photoURL: String?
        )

data class Account (
    var username:String?,
    var password: String?,
    var phone: String?,
    var photoURL: String?
)

data class Booking (
    val id:String?,
    var alamat: String?,
    var harga: Int?,
    var status_bayar: Int?,
    var bulan_sewa: Int?,
    var tahun_sewa: Int?,
    var metodePembayaran: String?,
    var photoURL: String?,
    var idKost: String?
)
data class Ulasan (
    val id:String?,
    var rating: Int?,
    var deskripsi: String?,
    var username: String?,
    var kostId: String?
        )

data class Favorite (
    val username:String?,
    val idKost:String?,
    var alamat: String?,
    var kamarTersedia: Int?,
    var deskripsi: String?,
    var harga: Int?,
    var jenisKelamin: String?,
    var phone: String?,
    var photoURL: String?
)
