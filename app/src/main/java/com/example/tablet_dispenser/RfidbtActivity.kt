package com.example.tablet_dispenser

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import app.akexorcist.bluetotohspp.library.BluetoothSPP
import app.akexorcist.bluetotohspp.library.BluetoothState
import app.akexorcist.bluetotohspp.library.DeviceList
import com.example.tablet_dispenser.databinding.ActivityRfidbtBinding

class RfidbtActivity : AppCompatActivity() {
    lateinit var bt: BluetoothSPP
    val binding: ActivityRfidbtBinding by lazy {
        ActivityRfidbtBinding.inflate(layoutInflater)
    }
    val dbhelper : DatabaseHelper by lazy{
        DatabaseHelper.getInstance(applicationContext)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        var a = 0
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnBtOut.visibility = View.GONE
        bt = BluetoothSPP(this) //Initializing
        if (!bt.isBluetoothAvailable) { //블루투스 사용 불가
            Toast.makeText(
                applicationContext, "Bluetooth is not available", Toast.LENGTH_SHORT
            ).show()
            finish()
        }
        bt.setOnDataReceivedListener { data, message ->
            //데이터 수신되면
            a = Integer.parseInt(message)
            binding.tvBtOk.text = "RFID : $a"
            binding.btnBtOut.visibility = View.VISIBLE
            Toast.makeText(this,message, Toast.LENGTH_SHORT).show() // 토스트로 데이터 띄움
        }
        bt.setBluetoothConnectionListener(object : BluetoothSPP.BluetoothConnectionListener {
            //연결됐을 때
            override fun onDeviceConnected(name: String, address: String) {
                Toast.makeText(
                    applicationContext, "Connected to $name\n$address", Toast.LENGTH_SHORT
                ).show()
                binding.tvBtOk.text = "RFID 카드를 태그하세요"
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
        binding.btnConnect.setOnClickListener {
            if (bt.serviceState == BluetoothState.STATE_CONNECTED) {
                bt.disconnect()
            } else {
                val intent = Intent(applicationContext, DeviceList::class.java)
                startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE)
            }
        }
        binding.btnBtOut.setOnClickListener {
            Myapplication.pref.setRfid("rfid",a)
            var intent = Intent(this,RfidinputmenuActivity::class.java)
            Toast.makeText(this,"setting RFID : $a", Toast.LENGTH_SHORT).show() // 토스트로 데이터 띄움
            startActivity(intent)

            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bt.stopService() //블루투스 중지
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