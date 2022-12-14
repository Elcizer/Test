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
    val binding : ActivityMenuBinding by lazy{
        ActivityMenuBinding.inflate(layoutInflater)
    }

    val dbhelper: DatabaseHelper by lazy{
        DatabaseHelper.getInstance(applicationContext)
    }

    val dbHelper_record : DatabaseHelper_record by lazy {
        DatabaseHelper_record.getInstance(applicationContext)
    }
    var rfid = Myapplication.pref.getRfid("rfid",0)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        var rfid = Myapplication.pref.getRfid("rfid",0)

        setContentView(binding.root)

        binding.tvRfidnum3.text = "RFID: $rfid"
        record()
        checkdata()
        rfid()
        pill()

        /*binding.btnRecord.setOnClickListener {
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
            var triple_pill = dbhelper.readPillData(rfid) // 이거 보내면 되는데 데이터 어떻게 보낼지는 가서 생각하지 머
            

        }

        binding.btnCheckdata.setOnClickListener {
            var intent_datacheck = Intent(this, rfidcheckdataActivity::class.java)
            startActivity(intent_datacheck)
        }
*/
    }

    override fun onRestart() {
        super.onRestart()
        var rfid = Myapplication.pref.getRfid("rfid",0)
        binding.tvRfidnum3.text = "RFID: $rfid"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun pill(){
        binding.btnPill.setOnClickListener {
            var intent_pill = Intent(this,PillActivity::class.java)
            startActivity(intent_pill)
            /*var now = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ")
            var Strnow = now.format(formatter)
            dbHelper_record.registerRecord(rfid,Strnow)
            var triple_pill = dbhelper.readPillData(rfid) */
        }
    }

    fun rfid(){
        binding.btnRfid.setOnClickListener {
            var intent_rfid = Intent(this,RfidinputmenuActivity::class.java)
            startActivity(intent_rfid)
        }
    }
    fun record(){
        binding.btnRecord.setOnClickListener {
            var intent_record = Intent(this,RecordActivity::class.java)
            startActivity(intent_record)
            var message = Myapplication.pref.getDate(2,2)
            Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()

        }

    }
    fun checkdata(){
        binding.btnCheckdata.setOnClickListener {
            var intent_datacheck = Intent(this, rfidcheckdataActivity::class.java)
            startActivity(intent_datacheck)
        }
    }
}