package com.example.alertdialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun showAlertDialog(view: View) {
        val builder : AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Do u love me")
        builder.setMessage("U should love me because i love u all of my heart")
        builder.setIcon(R.drawable.img)

            .setPositiveButton("Yes") { dialogInterface, _ ->
                // Handle "Undo" action
                Toast.makeText(applicationContext, "I love u too", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()  // Dismiss the dialog after action
            }
            .setNegativeButton("No") {dialogInterface, _ ->
                Toast.makeText(applicationContext, "NVM Girl", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
            }
            .setCancelable(false)
            .create()
            .show()
    }
}