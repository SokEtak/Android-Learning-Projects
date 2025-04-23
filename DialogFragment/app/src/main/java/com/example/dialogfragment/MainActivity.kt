package com.example.dialogfragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    lateinit var show:Button
    lateinit var name:TextView
    lateinit var age:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        show = findViewById(R.id.showDialogBtn)
        name = findViewById(R.id.textViewName)
        age = findViewById(R.id.textViewAge)

        show.setOnClickListener {
            val fragmentManager : FragmentManager = supportFragmentManager
            val myDialogFragment = MyDialogFragment()
            myDialogFragment.show(fragmentManager,"MyDialogFragment")
            myDialogFragment.isCancelable = false
        }
    }

    @SuppressLint("SetTextI18n")
    fun getData(userName:String, userAge :Int ){
        name.text="Name:${userName}"
        age.text="Name:${userAge}"
    }
}