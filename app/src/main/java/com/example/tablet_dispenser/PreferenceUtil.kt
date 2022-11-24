package com.example.tablet_dispenser

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {
    private val pref :SharedPreferences =
        context.getSharedPreferences("RFID",0)

    fun getRfid(key:String,defValue: Int): Int{
        return pref.getInt(key, defValue).toInt()
    }

    fun setRfid(key:String, Rfid:Int)
    {
        pref.edit().putInt(key,Rfid).apply()
    }
}