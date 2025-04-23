package com.example.sendsms

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.Manifest


class MainActivity : AppCompatActivity() {

    lateinit var message :EditText
    lateinit var number: EditText
    lateinit var button: Button

    var userMessage : String = ""
    var userNumber : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        message = findViewById(R.id.edMessage)
        number = findViewById(R.id.edNumber)
        button = findViewById(R.id.btnSend)

        button.setOnClickListener {
            userMessage = message.text.toString()
            userNumber = number.text.toString()
            sendMessage(userMessage,userNumber)
        }

    }
        //relate with permission to allow use it
        //user permission to make it work
        //must change it to sdk version 22 in build.gradle
        //rebuild after change version
        fun sendMessage(userMessage:String,userNumber: String){
            //handle on user permission(in manifest have permission and from user)
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
                //first call run this
                //default permission is not allow 
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 1)

            } else {
                val smsManager : SmsManager
                //work on both target when use with it
                if (Build.VERSION.SDK_INT>=23){
                    smsManager = this.getSystemService(SmsManager::class.java)//for version 23 +

                }else{
                    smsManager = SmsManager.getDefault()//for version 23-
                }
                smsManager.sendTextMessage(userNumber, null, userMessage,null, null)
                Toast.makeText(this,"Message Sent To $userNumber",Toast.LENGTH_SHORT).show()
            }
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        if (requestCode==1&&grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            sendMessage(userMessage,userNumber)
        }else{
            Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show()
        }

    }
}