package com.example.tablet_dispenser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tablet_dispenser.databinding.ActivityRfidpillsettingBinding

class RfidpillsettingActivity : AppCompatActivity() {

    val binding : ActivityRfidpillsettingBinding by lazy{
        ActivityRfidpillsettingBinding.inflate(layoutInflater)
    }

    private val dbHelper: DatabaseHelper by lazy {
        DatabaseHelper.getInstance(applicationContext)
    }
    var rfid = Myapplication.pref.getRfid("rfid",0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvRfidnumPill.setText("RFID: $rfid")
        changepillnum()

    }


    fun changepillnum()
    {
        binding.btnPillnumberChange.setOnClickListener{
            try{
                dbHelper.changePillData(
                    rfid,
                    Integer.parseInt(binding.etP1Setting.text.toString()),
                    Integer.parseInt(binding.etP2Setting.text.toString()),
                    Integer.parseInt(binding.etP3Setting.text.toString()),
                )
            }
            catch(e:Exception){
                e.printStackTrace()
            }
            finally{
                Toast.makeText(applicationContext,"적용되었습니다",Toast.LENGTH_SHORT).show() // 왜 안뜸 이거
                finish()
            }
        }
    }
}















