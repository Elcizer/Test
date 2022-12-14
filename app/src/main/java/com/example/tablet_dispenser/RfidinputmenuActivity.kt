package com.example.tablet_dispenser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tablet_dispenser.databinding.ActivityRfidinputmenuBinding

class RfidinputmenuActivity : AppCompatActivity() {

    val binding : ActivityRfidinputmenuBinding by lazy{
        ActivityRfidinputmenuBinding.inflate(layoutInflater)
    }

    private val dbHelper: DatabaseHelper by lazy {
        DatabaseHelper.getInstance(applicationContext)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var rfid = Myapplication.pref.getRfid("rfid",0)
        binding.tvRfidnum.setText("RFID: $rfid")


        var intent_register = Intent(this,RfidregisterActivity::class.java)
        var intent_info = Intent(this,RfidinfosettingActivity::class.java)
        var intent_pill = Intent(this,RfidpillsettingActivity::class.java)
        var intent_recognize = Intent(this,RfidbtActivity::class.java)

        binding.btnRegister.setOnClickListener {
            if(dbHelper.checkDataExists(rfid)){
                Toast.makeText(this,"이미 등록된 RFID 입니다. 정보 변경을 이용해주세요.",Toast.LENGTH_SHORT).show()
            }
            else
            {
                startActivity(intent_register)
            }
        }
        binding.btnInfosetting.setOnClickListener {
            startActivity(intent_info)
            finish()
        }
        binding.btnPillsetting.setOnClickListener {
            startActivity(intent_pill)
            finish()
        }
        binding.btnRfidRecognize.setOnClickListener {
            startActivity(intent_recognize)
            finish()
        }
    }
    override fun onRestart() {
        super.onRestart()
        var rfid = Myapplication.pref.getRfid("rfid",0)
        binding.tvRfidnum.text = "RFID: $rfid"
    }
}