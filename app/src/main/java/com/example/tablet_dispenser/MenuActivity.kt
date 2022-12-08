package com.example.tablet_dispenser

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.tablet_dispenser.databinding.ActivityMenuBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MenuActivity : AppCompatActivity() {



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        var rfid = Myapplication.pref.getRfid("rfid",0)

        var binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper_record : DatabaseHelper_record by lazy {
            DatabaseHelper_record.getInstance(applicationContext)
        }

        binding.btnRfid.setOnClickListener {
            var intent_rfid = Intent(this,RfidinputActivity::class.java)
            startActivity(intent_rfid)

        }
        binding.btnRecord.setOnClickListener {
            var intent_record = Intent(this,RecordActivity::class.java)
            startActivity(intent_record)
            var message = Myapplication.pref.getDate(2,2)
            Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()

        }
        binding.btnPill.setOnClickListener {
            var intent_pill = Intent(this,PillActivity::class.java)
            var now = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ")
            var Strnow = now.format(formatter)
            dbHelper_record.registerRecord(rfid,Strnow)
        }

        binding.btnCheckdata.setOnClickListener {
            var intent_datacheck = Intent(this,rfidcheckdataActivity::class.java)
            startActivity(intent_datacheck)
            finish()
        }

    }
}