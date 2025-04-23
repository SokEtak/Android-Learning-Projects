package com.example.ageinminutescalulatorudemy

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    @SuppressLint("SetTextI18n")
    fun clickDatePicker() {
        val selectedDate = findViewById<TextView>(R.id.selectedDate)
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val month = myCalender.get(Calendar.MONTH)//month in number format ,ex january:0,start from 0 not 1

        //make the code shorter
        val datePickerDialog = DatePickerDialog(
            this, { _, selectedYear, selectedMonth, selectedDay ->
                    //some part might look different from vdo
                Toast.makeText(this, "Year:${selectedYear}-Month:${selectedMonth+1}-Day:${selectedDay}", Toast.LENGTH_SHORT)
                    .show()

                val selectedDateValue = "${selectedDay}/${selectedMonth+1}/${selectedYear}"
                selectedDate.text = selectedDateValue
                val sdf = SimpleDateFormat("dd/mmmm/yyyy", Locale.CHINA)
                val theDate = sdf.parse(selectedDateValue)
                theDate?.let {//safely purpose(prevent from crashing)
                    val selectedDateInMinutes =
                        theDate.time.div(60000) //60000 millisecond = 1 minute
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate.let {
                        val currentDateInMinute = (currentDate?.time?.div(60000))
                        val difference = (currentDateInMinute?.minus(selectedDateInMinutes))
                        val differenceInMinuteView = findViewById<TextView>(R.id.differenceInMinute)
                        differenceInMinuteView.text = difference.toString()
                    }

                }
                /*more info:vdo-310*/

            },
            year, month, day
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 8640000
        datePickerDialog.show()

    }
}