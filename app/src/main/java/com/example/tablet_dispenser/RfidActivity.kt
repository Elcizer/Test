package com.example.tablet_dispenser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tablet_dispenser.databinding.ActivityRfidBinding

class RfidActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityRfidBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}