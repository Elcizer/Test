package com.example.tablet_dispenser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tablet_dispenser.databinding.ActivityRfidinputBinding

class RfidinputActivity : AppCompatActivity() {
    private val binding :ActivityRfidinputBinding by lazy{
        ActivityRfidinputBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnEnter.setOnClickListener {
            var intent = Intent(this,RfidinputmenuActivity::class.java)
            Myapplication.pref.setRfid("rfid",Integer.parseInt(binding.etNum.text.toString()))
            startActivity(intent)
            finish()
        } // RFID 숫자를 입력하고 그 integer형 숫자를 SharedPreference에 저장
    }
    /*private fun savedata() {
        val pref = getSharedPreferences("pref",0)
        val edit = pref.edit()  //수정모드
        edit.putInt("RFID",Integer.parseInt(binding.etNum.text.toString())) // 1번쨰는 키 값, 2번째는 변수
        edit.apply() // 저장
    }*/
}