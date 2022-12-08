package com.example.tablet_dispenser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tablet_dispenser.databinding.ActivityRecordBinding

class RecordActivity : AppCompatActivity() {
    private val dbHelper_record : DatabaseHelper_record by lazy {
        DatabaseHelper_record.getInstance(applicationContext)
    }
    private val binding : ActivityRecordBinding by lazy{
        ActivityRecordBinding.inflate(layoutInflater)
    }
    var rfid = Myapplication.pref.getRfid("rfid",0)
    override fun onCreate(savedInstanceState: Bundle?) { //액티비티 실행 지점
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var rfid = Myapplication.pref.getRfid("rfid",0)
        binding.tvRfidnum4.setText("RFID: $rfid")

        var dataset: ArrayList<Pair<String,String>> = arrayListOf()
        dataset = dbHelper_record.read_record(rfid)

        binding.recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.adapter = RecyclerViewAdapter(dataset)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL))
    }

}