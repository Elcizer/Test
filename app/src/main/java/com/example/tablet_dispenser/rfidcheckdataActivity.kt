package com.example.tablet_dispenser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tablet_dispenser.databinding.ActivityRfidcheckdataBinding

class rfidcheckdataActivity : AppCompatActivity() {

    private val dbHelper: DatabaseHelper by lazy {
        DatabaseHelper.getInstance(applicationContext)
    }

    private val binding : ActivityRfidcheckdataBinding by lazy{
        ActivityRfidcheckdataBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var rfid = Myapplication.pref.getRfid("rfid",0)
        var pair_check = dbHelper.readUserData(rfid)
        var triple_check = dbHelper.readPillData(rfid)

        var name = pair_check.component1()
        var birthday = pair_check.component2()
        var p1 = triple_check.component1()
        var p2 = triple_check.component2()
        var p3 = triple_check.component3()

        binding.tvRfidnum2.setText("RFID: $rfid")
        binding.tvInquireName.setText("이름: $name")
        binding.tvInquireBirthday.setText("생일: $birthday")
        binding.tvInquireP1.setText("P1: $p1")
        binding.tvInquireP2.setText("P2: $p2")
        binding.tvInquireP3.setText("P3: $p3")


    }



}