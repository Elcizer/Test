package com.example.tablet_dispenser

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tablet_dispenser.databinding.ActivityMainBinding
import com.example.tablet_dispenser.databinding.ActivityRfidinputBinding
import com.example.tablet_dispenser.databinding.ActivityRfidregisterBinding
import com.example.tablet_dispenser.databinding.ActivityRfidregistercheckBinding

class RfidregisterActivity : AppCompatActivity() {
    private val binding :ActivityRfidregisterBinding by lazy{
        ActivityRfidregisterBinding.inflate(layoutInflater)
    }

    private val dbHelper: DatabaseHelper by lazy {
        DatabaseHelper.getInstance(applicationContext)
    }
    var rfid = Myapplication.pref.getRfid("rfid",0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.tvRfidnum.text = "RFID: $rfid"
        insertInfo()
    }
    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }

    private fun insertInfo()
    {
        binding.btnRegisterCheck.setOnClickListener {
            var intent_check = Intent(this,RfidregistercheckActivity::class.java)
            try{
                dbHelper.RegisterData(
                    rfid,
                    binding.etName.text.toString().trim(),
                    binding.etBirthday.text.toString().trim()
                )
            }
            catch(e:Exception){
                e.printStackTrace()
            }
            startActivity(intent_check)
            finish()
        }

    }
}