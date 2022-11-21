package com.example.tablet_dispenser

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tablet_dispenser.databinding.ActivityMainBinding
import com.example.tablet_dispenser.databinding.ActivityRfidregisterBinding
import com.example.tablet_dispenser.databinding.ActivityRfidregistercheckBinding

class RfidregisterActivity : AppCompatActivity() {

    private val dbHelper: DatabaseHelper by lazy {
        DatabaseHelper.getInstance(applicationContext)
    }
    var rfid = intent.getIntExtra("rfid_register",0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityRfidregisterBinding.inflate(layoutInflater)

        binding.tvRfidnum.text = "RFID : $rfid"

        setContentView(R.layout.activity_rfidregister)
        setContentView(binding.root)


    }
    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }

    private fun insertInfo()
    {
        var binding = ActivityRfidregisterBinding.inflate(layoutInflater)
        binding.btnRegisterCheck.setOnClickListener {
            var intent_check = Intent(this,ActivityRfidregistercheckBinding::class.java)
            intent_check.getIntExtra("check_rfid",rfid)
            try{
                dbHelper.insertUserData(
                    rfid.toString().trim(),
                    binding.etName.text.toString().trim(),
                    binding.etBirthday.text.toString().trim()
                )
            }
            catch(e:Exception){
                e.printStackTrace()
            }
            startActivity(intent)
            finish()
        }

    }
}