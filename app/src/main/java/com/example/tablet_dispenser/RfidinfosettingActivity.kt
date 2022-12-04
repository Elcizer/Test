package com.example.tablet_dispenser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tablet_dispenser.databinding.ActivityRfidinfosettingBinding

class RfidinfosettingActivity : AppCompatActivity() {

   val binding : ActivityRfidinfosettingBinding by lazy{
       ActivityRfidinfosettingBinding.inflate(layoutInflater)
   }

    val dbHelper : DatabaseHelper by lazy{
        DatabaseHelper.getInstance(applicationContext)
    }

    var rfid = Myapplication.pref.getRfid("rfid",0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var pair_change_info = dbHelper.readUserData(rfid)
        var name = pair_change_info.component1()
        var birthday = pair_change_info.component2()

        binding.tvRfidnumInfo.setText("RFID : $rfid")
        binding.etNameInfo.setText(name)
        binding.etBirthdayInfo.setText(birthday)
        changeinfo()
    }

    fun changeinfo(){
        binding.btnInfoCheck.setOnClickListener {
            try{
            dbHelper.changeUserData(
                rfid,
                binding.etNameInfo.text.toString().trim(),
                binding.etBirthdayInfo.text.toString().trim(),
            )
            }
            catch(e:Exception){
                e.printStackTrace()
            }
            finally{
                Toast.makeText(applicationContext,"적용되었습니다", Toast.LENGTH_SHORT).show() // 왜 안뜸 이거
                finish()
            }
        }
    }
}