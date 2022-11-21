package com.example.tablet_dispenser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tablet_dispenser.databinding.ActivityMainBinding
import com.example.tablet_dispenser.databinding.ActivityRfidregisterBinding
import com.example.tablet_dispenser.databinding.ActivityRfidregistercheckBinding

class RfidregistercheckActivity : AppCompatActivity() {
    private val binding: ActivityRfidregistercheckBinding by lazy {
        ActivityRfidregistercheckBinding.inflate(layoutInflater)
    }

    private val dbHelper: DatabaseHelper by lazy {
        DatabaseHelper.getInstance(applicationContext)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rfidregistercheck)
        setContentView(binding.root)
        var rfid = intent.getIntExtra("rfid_check",0)
        var user : Pair<String,String> = dbHelper.readUserData(rfid.toString())
        binding.tvNameCheck.text = user.first
        binding.tvBirthdayCheck.text = user.second
        binding.btnCheckFinish.setOnClickListener {
            finish()
        }

    }
}