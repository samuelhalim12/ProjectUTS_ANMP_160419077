package com.ubaya.anmp_projectuts_160419077.view

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ubaya.anmp_projectuts_160419077.model.Booking
import com.ubaya.anmp_projectuts_160419077.model.Kost

interface ButtonSubmitUlasanClickListener {
    fun onButtonSubmitUlasanClick(v: View)
}
interface ButtonSaveChangesEditProfile {
    fun onButtonSaveChangesEditProfile(v: View)
}
interface ButtonUlasanClickListener {
    fun onButtonUlasanClickListener(v:View)
}
interface ButtonSewaClickListener {
    fun onButtonSewaClickListener(v:View)
}
interface RadioStatusBayarListener {
    fun onRadioStatusBayarListener(v: View, status_bayar:Int, obj:Booking?)
}

interface ButtonDetailListener {
    fun onButtonDetailListener(v: View)
}

interface ButtonDetailFavoriteListener {
    fun onButtonDetailFavoriteListener(v: View)
}
interface ButtonFavoriteListener {
    fun onButtonFavoriteListener(v: View)
}
interface ButtonAddFavoriteListener {
    fun onButtonAddFavoriteListener(v: View)
}
interface ButtonDetailBookingListener {
    fun onButtonDetailBookingListener(v: View)
}
interface ButtonDetailKostDetailBookingListener {
    fun onButtonDetailKostDetailBookingListener(v: View)
}
interface ButtonBeriUlasanListener {
    fun onButtonBeriUlasanListener(v: View)
}
interface ButtonEditMyKostListener {
    fun onButtonEditMyKostListener(v: View)
}
interface ButtonAddMyKostClick {
    fun onButtonAddMyKostClick(v: View)
}
interface ButtonCreateClick {
    fun onButtonCreateClick(v: View)
}
interface RadioJenisKelaminListener {
    fun onRadioJenisKelaminListener(v: View, jenisKelamin:String, obj:Kost)
}
//interface RadioEditJenisKelaminListener {
//    fun onRadioEditJenisKelaminListener(v: View, jenisKelamin:String, obj:Kost)
//}
interface ButtonSaveEditKostClick {
    fun onButtonSaveEditKostClick(v: View)
}
