package com.example.todo_app

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var item : EditText
    lateinit var button : Button
    lateinit var listView : ListView

    var itemList = ArrayList<String>()
    var fileHelper = FileHelper()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        item = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        listView = findViewById(R.id.listView)
        itemList = fileHelper.readData(applicationContext)

        var arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,itemList)
        listView.adapter = arrayAdapter

        button.setOnClickListener {
            var itemName:String = item.text.toString()
            itemList.add(itemName)
            item.setText("") //set text to null
            fileHelper.writeDate(itemList,applicationContext)
            arrayAdapter.notifyDataSetChanged()
        }

        listView.setOnItemClickListener { parent, view, position, id ->

            var alert = AlertDialog.Builder(this@MainActivity)

            alert.setTitle("Delete")
            alert.setMessage("Do you want to delete this item?")

            alert.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })

            alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                itemList.removeAt(position)
            })

            alert.create().show()

        }
    }
}