package com.ubaya.anmp_projectuts_160419077.model
import com.google.gson.annotations.SerializedName
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(tableName = "kost")
data class Kost (
    @ColumnInfo(name="alamat")
    var alamat: String?,
    @ColumnInfo(name="kamarTersedia")
    var kamarTersedia: Int?,
    @ColumnInfo(name="deskripsi")
    var deskripsi: String?,
    @ColumnInfo(name="harga")
    var harga: Int?,
    @ColumnInfo(name="jenisKelamin")
    var jenisKelamin: String?,
    @ColumnInfo(name="phone")
    var phone: String?,
    @ColumnInfo(name="photoURL")
    var photoURL: String?,
    @ColumnInfo(name="username")
    var username: String?
        ) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
@Entity(tableName = "account")
data class Account (
    @PrimaryKey
    var username: String,
    @ColumnInfo(name="password")
    var password: String?,
    @ColumnInfo(name="phone")
    var phone: String?,
    @ColumnInfo(name="photoURL")
    var photoURL: String?
) {

}
@Entity(tableName = "booking")
data class Booking (
    @ColumnInfo(name="alamat")
    var alamat: String?,
    @ColumnInfo(name="harga")
    var harga: Int?,
    @ColumnInfo(name="status_bayar")
    var status_bayar: Int?,
    @ColumnInfo(name="bulan_sewa")
    var bulan_sewa: Int?,
    @ColumnInfo(name="tahun_sewa")
    var tahun_sewa: Int?,
    @ColumnInfo(name="metodePembayaran")
    var metodePembayaran: String?,
    @ColumnInfo(name="photoURL")
    var photoURL: String?,
    @ColumnInfo(name="idKost")
    var idKost: Int?
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
@Entity(tableName = "ulasan")
data class Ulasan (
    @ColumnInfo(name="rating")
    var rating: Int?,
    @ColumnInfo(name="deskripsi")
    var deskripsi: String?,
    @ColumnInfo(name="username")
    var username: String?,
    @ColumnInfo(name="kostId")
    var kostId: Int?
        ) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
@Entity(tableName = "favorite")
data class Favorite (
    @ColumnInfo(name="alamat")
    var alamat: String?,
    @ColumnInfo(name="harga")
    var harga: Int?,
    @ColumnInfo(name="username")
    var username:String?,
    @ColumnInfo(name="photoURL")
    var photoURL: String?,
    @ColumnInfo(name="idKost")
    var idKost:Int?
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}