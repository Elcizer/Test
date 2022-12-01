package com.example.tablet_dispenser

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.tablet_dispenser.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
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
        //startActivity(intent_record)
            var message = Myapplication.pref.getDate(2,2)
            Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()

        }
        binding.btnPill.setOnClickListener {
            var intent_pill = Intent(this,PillActivity::class.java)
            Myapplication.pref.setDate(2,2) //여기에 DatabaseHelper 를 바꿨으니까 이제 여기서도 바꿔야함
            //                                                                    index 업데이트 하는거 업데이트 했으니까
            //                                                                     내일의 내가 알아서 해~
        //startActivity(intent_pill)
        }

    }
}