package com.example.tablet_dispenser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.tablet_dispenser.databinding.ActivityRecordBinding

class RecordActivity : AppCompatActivity() {

    var userlist = arrayListOf<User>(
        User(R.drawable.user, "이경하", "20"),
    )

    override fun onCreate(savedInstanceState: Bundle?) { //액티비티 실행 지점


        super.onCreate(savedInstanceState)
        val binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val user = arrayOf("abc","def","ghi","jkl","mno")
//        binding.listView.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,user)

        val Adapter = UserAdapter(this, userlist)
        binding.listView.adapter = Adapter

        binding.listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectItem = parent.getItemAtPosition(position) as User
            Toast.makeText(this,selectItem.name, Toast.LENGTH_SHORT).show()

        }


    }
}