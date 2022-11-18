package com.example.tablet_dispenser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tablet_dispenser.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        var binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRfid.setOnClickListener {
            var intent_rfid = Intent(this,RfidinputActivity::class.java)
            startActivity(intent_rfid)

        }
        binding.btnRecord.setOnClickListener {
            var intent_record = Intent(this,RecordActivity::class.java)
            startActivity(intent_record)

        }
        binding.btnPill.setOnClickListener {
            var intent_pill = Intent(this,PillActivity::class.java)
            startActivity(intent_pill)

        }

    }
}