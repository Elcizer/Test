package com.example.tablet_dispenser

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PreferenceUtil(context: Context) {
    private val pref :SharedPreferences =
        context.getSharedPreferences("RFID",0)

    fun getRfid(key:String,defValue: Int): Int{
        return pref.getInt(key, defValue)
    }

    fun setRfid(key:String, Rfid:Int)
    {
        pref.edit().putInt(key,Rfid).apply()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setDate(rfid:Int, index_of_take:Int)
    {
        var now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ")
        var Strnow = now.format(formatter)
        pref.edit().putString("rfid:$rfid,index:$index_of_take",Strnow).apply()
    }

    fun getDate(rfid:Int, index_of_take: Int): String? {
        return pref.getString("rfid:$rfid,index:$index_of_take","")
    }
}
