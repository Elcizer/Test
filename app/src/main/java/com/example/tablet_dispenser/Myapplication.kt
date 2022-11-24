package com.example.tablet_dispenser

import android.app.Application

class Myapplication: Application() {
    companion object{
        lateinit var pref : PreferenceUtil
    }

    override fun onCreate() {
        pref = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}