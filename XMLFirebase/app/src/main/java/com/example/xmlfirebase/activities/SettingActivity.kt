package com.example.xmlfirebase.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.xmlfirebase.R
import com.example.xmlfirebase.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    lateinit var settingBinding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_setting)
        //bound tip
        Toast.makeText(this, "Opened from Navigation Drawer", Toast.LENGTH_SHORT).show()

        // ✅ Initialize view binding here
        settingBinding = ActivitySettingBinding.inflate(layoutInflater)
        val view = settingBinding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        settingBinding.btnForgetPass.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}