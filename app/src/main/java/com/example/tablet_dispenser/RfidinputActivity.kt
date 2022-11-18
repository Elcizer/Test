package com.example.tablet_dispenser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tablet_dispenser.databinding.ActivityRfidinputBinding

class RfidinputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityRfidinputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnEnter.setOnClickListener {
            var input_num = Integer.parseInt(binding.etNum.text.toString())
            var intent = Intent(this,RfidinputmenuActivity::class.java)
            intent.putExtra("rfid_num",input_num)
            startActivity(intent)
            finish()
        } // RFID 숫자를 입력하고 그 integer형 숫자를 RfidActivity에 전달한다.
    }
}