package com.example.scheduleapp.activities

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider // Import ViewModelProvider
import com.example.scheduleapp.MyApp // Import your custom Application class
import com.example.scheduleapp.databinding.ActivityAddTodoBinding
import com.example.scheduleapp.models.Todo
import com.example.scheduleapp.viewModels.TodosViewModel
import com.example.scheduleapp.viewModels.TodosViewModelFactory // Import your ViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class AddTodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTodoBinding
    lateinit var todosViewModel : TodosViewModel // This will now be properly initialized
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the ViewModel using the factory and your custom Application class
        val todosViewModelFactory = TodosViewModelFactory((application as MyApp).todosRepository)
        todosViewModel = ViewModelProvider(this, todosViewModelFactory)[TodosViewModel::class.java]

        setupDatePickers()
        setupSaveButton()
    }

    private fun setupDatePickers() {
        // Set TimeZone for consistency if needed, although DatePickerDialog handles local timezone
        // The default TimeZone for the device will usually suffice.
        calendar.timeZone = TimeZone.getDefault()

        // Start Date DatePicker
        binding.etStartDate.setOnClickListener {
            showDatePickerDialog(binding.etStartDate)
        }

        // Due Date DatePicker
        binding.etDueDate.setOnClickListener {
            showDatePickerDialog(binding.etDueDate)
        }
    }

    private fun showDatePickerDialog(dateEditText: com.google.android.material.textfield.TextInputEditText) {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                calendar.set(selectedYear, selectedMonth, selectedDay)
                // Format the date to display it in the EditText
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                dateEditText.setText(dateFormat.format(calendar.time))
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun setupSaveButton() {
        binding.btnSaveTodo.setOnClickListener {
            saveTodo()
        }
    }

    private fun saveTodo() {
        val title = binding.etTitle.text.toString().trim()
        val description = binding.etDescription.text.toString().trim()
        val startDateString = binding.etStartDate.text.toString().trim()
        val dueDateString = binding.etDueDate.text.toString().trim()
        val place = binding.etPlace.text.toString().trim()

        // Basic validation: Check if required fields are not empty
        if (title.isEmpty()) {
            binding.tilTitle.error = "Title cannot be empty"
            return
        } else {
            binding.tilTitle.error = null // Clear error if valid
        }

        if (startDateString.isEmpty()) {
            binding.tilStartDate.error = "Start Date cannot be empty"
            return
        } else {
            binding.tilStartDate.error = null
        }

        if (dueDateString.isEmpty()) {
            binding.tilDueDate.error = "Due Date cannot be empty"
            return
        } else {
            binding.tilDueDate.error = null
        }

        // Parse dates from string to Date objects
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val startDate: Date
        val dueDate: Date
        try {
            startDate = dateFormat.parse(startDateString) ?: Date()
            dueDate = dateFormat.parse(dueDateString) ?: Date()

            // Optional: Basic date logic validation (due date should not be before start date)
            if (dueDate.before(startDate)) {
                binding.tilDueDate.error = "Due Date cannot be before Start Date"
                Toast.makeText(this, "Due Date must be on or after Start Date.", Toast.LENGTH_LONG).show()
                return
            }

        } catch (e: Exception) {
            Toast.makeText(this, "Error parsing dates. Please select valid dates.", Toast.LENGTH_LONG).show()
            return
        }

        // Create the Todos object
        val newTodo = Todo(
            id = 0, // ID will be auto-generated by your Room database
            tittle = title,
            description = if (description.isEmpty()) null else description, // Store null if empty
            startDate = startDate,
            dueDate = dueDate,
            isComplete = false, // New todos always start as incomplete
            place = if (place.isEmpty()) null else place, // Store null if empty
            completedDate = null // Not completed yet
        )

        // INSERTING THE TODO
        todosViewModel.insert(newTodo)

        Toast.makeText(this, "Todo '${newTodo.tittle}' saved!", Toast.LENGTH_SHORT).show()

        finish()
    }
}