package com.example.tablet_dispenser

import android.app.ProgressDialog.show
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import app.akexorcist.bluetotohspp.library.BluetoothSPP
import app.akexorcist.bluetotohspp.library.BluetoothState
import app.akexorcist.bluetotohspp.library.DeviceList
import com.example.tablet_dispenser.databinding.ActivityPillBinding

class PillActivity : AppCompatActivity() {
    lateinit var bt: BluetoothSPP
    lateinit var abc : String
    var rfid = Myapplication.pref.getRfid("rfid",0)
    val binding: ActivityPillBinding by lazy{
       ActivityPillBinding.inflate(layoutInflater)
    }
    val dbhelper : DatabaseHelper by lazy{
        DatabaseHelper.getInstance(applicationContext)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pill)
        setContentView(binding.root)
        var a=0

        binding.btnBtOut2.visibility = View.GONE
        bt = BluetoothSPP(this) //Initializing
        if (!bt.isBluetoothAvailable) { //블루투스 사용 불가
            Toast.makeText(
                applicationContext, "Bluetooth is not available", Toast.LENGTH_SHORT
            ).show()
            finish()
        }
        bt.setOnDataReceivedListener { data, message ->
            //데이터 수신되면
            a= message.toInt()
            binding.tvBtOk2.text = "RFID : $a"
            binding.btnBtOut2.visibility = View.VISIBLE
            Toast.makeText(this,"$a", Toast.LENGTH_SHORT).show() // 토스트로 데이터 띄움
        }
        bt.setBluetoothConnectionListener(object : BluetoothSPP.BluetoothConnectionListener{
            // 연결됐을 때
            override fun onDeviceConnected(name: String, address: String) {
                Toast.makeText(
                    applicationContext, "Connected to $name\n$address", Toast.LENGTH_SHORT
                ).show()
                var triple_pill = dbhelper.readPillData(rfid)
                var p1 = triple_pill.component1()
                var p2 = triple_pill.component2()
                var p3 = triple_pill.component3()
                binding.tvBtOk2.text = "p1: $p1\np2: $p2\np3: $p3"
                binding.btnBtOut2.visibility = View.VISIBLE
            }

            override fun onDeviceDisconnected() { //연결해제
                Toast.makeText(
                    applicationContext, "Connection lost", Toast.LENGTH_SHORT
                ).show()
            }

            override fun onDeviceConnectionFailed() { //연결실패
                Toast.makeText(
                    applicationContext, "Unable to connect", Toast.LENGTH_SHORT
                ).show()
            }
        })//연결시도
        binding.btnConnectPill.setOnClickListener {
            if (bt.serviceState == BluetoothState.STATE_CONNECTED) {
                bt.disconnect()
            } else {
                val intent = Intent(applicationContext, DeviceList::class.java)
                startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE)
            }
        }
        setup()
    }
    fun setup() { //데이터 전송
        binding.btnBtOut2.setOnClickListener {
            var triple_pill = dbhelper.readPillData(rfid)
            var p1 = triple_pill.component1()
            var p2 = triple_pill.component2()
            var p3 = triple_pill.component3()
            abc = "$p1$p2$p3"
            val bytes = abc.toByteArray()
            Toast.makeText(applicationContext,bytes.toString(),Toast.LENGTH_SHORT).show()
            bt.send(bytes,true)
            finish()
        }
    }
    override fun onStart() {
        super.onStart()
        if (!bt.isBluetoothEnabled) { //
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(intent, BluetoothState.REQUEST_ENABLE_BT)
        } else {
            if (!bt.isServiceAvailable) {
                bt.setupService()
                bt.startService(BluetoothState.DEVICE_OTHER) //DEVICE_ANDROID는 안드로이드 기기 끼리
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == RESULT_OK) bt.connect(data)
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                bt.setupService()
                bt.startService(BluetoothState.DEVICE_OTHER)
            } else {
                Toast.makeText(
                    applicationContext, "Bluetooth was not enabled.", Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }


}