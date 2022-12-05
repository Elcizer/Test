package com.example.tablet_dispenser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.tablet_dispenser.databinding.ActivityRecordBinding

class RecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) { //액티비티 실행 지점


        super.onCreate(savedInstanceState)
        val binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}