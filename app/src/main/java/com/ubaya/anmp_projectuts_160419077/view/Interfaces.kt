package com.ubaya.anmp_projectuts_160419077.view

import android.view.View
import com.ubaya.anmp_projectuts_160419077.model.Booking

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

interface ButtonAddFavoriteListener {
    fun onButtonAddFavoriteListener(v: View)
}