package com.example.tablet_dispenser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tablet_dispenser.databinding.ActivityRfidpillsettingBinding

class RfidpillsettingActivity : AppCompatActivity() {

    val binding : ActivityRfidpillsettingBinding by lazy{
        ActivityRfidpillsettingBinding.inflate(layoutInflater)
    }

    private val dbHelper: DatabaseHelper by lazy {
        DatabaseHelper.getInstance(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var rfid = Myapplication.pref.getRfid("rfid",0)
        binding.tvRfidnumPill.setText("RFID: $rfid")




    }
}