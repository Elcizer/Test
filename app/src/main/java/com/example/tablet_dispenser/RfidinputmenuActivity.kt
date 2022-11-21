package com.example.tablet_dispenser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tablet_dispenser.databinding.ActivityRfidinputmenuBinding

class RfidinputmenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var binding = ActivityRfidinputmenuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var rfid = intent.getIntExtra("rfid_num",0)
        binding.tvRfidnum.text = "RFID : $rfid"

        var intent_register = Intent(this,RfidregisterActivity::class.java)
        var intent_info = Intent(this,RfidinfosettingActivity::class.java)
        var intent_pill = Intent(this,RfidpillsettingActivity::class.java)
        intent_register.putExtra("rfid_register",rfid)
        intent_info.putExtra("rfid_info",rfid)
        intent_pill.putExtra("rfid_pill",rfid)

        binding.btnRegister.setOnClickListener {
            startActivity(intent_register)
            finish()
        }
        binding.btnInfosetting.setOnClickListener {
            startActivity(intent_info)
            finish()
        }
        binding.btnPillsetting.setOnClickListener {
            startActivity(intent_pill)
            finish()
        }
    }
}