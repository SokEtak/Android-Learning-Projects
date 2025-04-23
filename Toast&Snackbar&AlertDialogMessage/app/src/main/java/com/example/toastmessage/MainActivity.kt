package com.example.toastmessage

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Adjusting the padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize the button by calling findViewById directly
        val btnToast: Button = findViewById<Button>(R.id.buttonToast)
        val btnSnackbar: Button = findViewById<Button>(R.id.buttonSnackbar)
        val mainL = findViewById<ConstraintLayout>(R.id.main)
        val btnDiaLog = findViewById<Button>(R.id.dialogBtn)

        //show toast message when button clicked
        btnToast.setOnClickListener {
            // Show a toast message when the button is clicked
            Toast.makeText(this, "This is a toast message", Toast.LENGTH_SHORT).show()
        }
        //show Snack bar message when button clicked
        btnSnackbar.setOnClickListener {
            Snackbar.make(mainL,"Do u love me?",Snackbar.LENGTH_INDEFINITE).setAction("Yes"){}.show()
        }
        //show AlertDialog message when button clicked
       btnDiaLog.setOnClickListener {
           showAlertDialog()
       }

    }
   private fun showAlertDialog(){
        val builder : AlertDialog.Builder = AlertDialog.Builder(this)
            .setTitle("Love Me Not")
            .setMessage("U should love me while u alive sissy")
        builder.setIcon(R.drawable.baseline_warning_24)

            .setPositiveButton("Yes",DialogInterface.OnClickListener{
                    dialogInterface, _ ->
                Toast.makeText(applicationContext, "I love u too", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()  // Dismiss the dialog after action
            })
//               .setCancelable(false)
            .setNegativeButton("No",DialogInterface.OnClickListener {dialogInterface, _ ->
                Toast.makeText(applicationContext, "NVM Girl", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
            }) //.setCancelable(false)
            .create().show()
    }
}
