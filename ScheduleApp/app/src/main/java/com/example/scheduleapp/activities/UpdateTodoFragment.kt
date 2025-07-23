package com.example.scheduleapp.activities

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.scheduleapp.MyApp
import com.example.scheduleapp.databinding.FragmentUpdateTodoBinding // Correct binding for this fragment
import com.example.scheduleapp.models.Todo
import com.example.scheduleapp.viewModels.TodosViewModel
import com.example.scheduleapp.viewModels.TodosViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class UpdateTodoFragment : Fragment() {

    private var _binding: FragmentUpdateTodoBinding? = null
    private val binding get() = _binding!!

    private lateinit var todosViewModel: TodosViewModel
    private val calendar = Calendar.getInstance()

    private var currentTodoId: Int = -1
    private var currentTodo: Todo? = null // To hold the todo object being updated

    companion object {
        // Use a constant for the argument key
        private const val ARG_TODO_ID = "todo_id"

        // Factory method to create an instance with arguments
        fun newInstance(todoId: Int): UpdateTodoFragment {
            val fragment = UpdateTodoFragment()
            val args = Bundle()
            args.putInt(ARG_TODO_ID, todoId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retrieve the todo ID from arguments
        arguments?.let {
            currentTodoId = it.getInt(ARG_TODO_ID, -1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        if (currentTodoId != -1) {
            // Observe the specific todo item by its ID
            todosViewModel.getTodoById(currentTodoId).observe(viewLifecycleOwner) { todo ->
                todo?.let {
                    currentTodo = it // Store the fetched todo
                    populateFields(it)
                } ?: run {
                    // Handle case where todo is not found (e.g., deleted elsewhere)
                    Toast.makeText(requireContext(), "Todo not found.", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.popBackStack() // Go back to previous fragment
                }
            }
        } else {
            // If no ID is passed, this fragment cannot function correctly
            Toast.makeText(requireContext(), "Error: No todo ID provided for update.", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }

        setupDatePickers()
        setupSaveButton()
    }

    private fun initViewModel() {
        val todosViewModelFactory = TodosViewModelFactory((requireActivity().application as MyApp).todosRepository)
        todosViewModel = ViewModelProvider(this, todosViewModelFactory)[TodosViewModel::class.java]
    }

    private fun populateFields(todo: Todo) {
        binding.etTitle.setText(todo.tittle)
        binding.etDescription.setText(todo.description)

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        binding.etStartDate.setText(dateFormat.format(todo.startDate))
        binding.etDueDate.setText(dateFormat.format(todo.dueDate))
        binding.etPlace.setText(todo.place)
        // If you had a checkbox for completion in this fragment, you'd set its state here
        // binding.cbIsComplete.isChecked = todo.isComplete
        binding.btnSaveTodo.text = "Update Todo" // Change button text to indicate update mode
    }

    private fun setupDatePickers() {
        calendar.timeZone = TimeZone.getDefault() // Ensure correct timezone for date pickers

        binding.etStartDate.setOnClickListener {
            showDatePickerDialog(binding.etStartDate)
        }

        binding.etDueDate.setOnClickListener {
            showDatePickerDialog(binding.etDueDate)
        }
    }

    private fun showDatePickerDialog(dateEditText: TextInputEditText) {
        val currentCalendar = Calendar.getInstance()
        val existingDateString = dateEditText.text.toString()
        if (existingDateString.isNotEmpty()) {
            try {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val existingDate = dateFormat.parse(existingDateString)
                existingDate?.let { currentCalendar.time = it }
            } catch (e: Exception) {
                // If parsing fails, default to current date
            }
        }

        val year = currentCalendar.get(Calendar.YEAR)
        val month = currentCalendar.get(Calendar.MONTH)
        val day = currentCalendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                currentCalendar.set(selectedYear, selectedMonth, selectedDay)
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                dateEditText.setText(dateFormat.format(currentCalendar.time))
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun setupSaveButton() {
        binding.btnSaveTodo.setOnClickListener {
            updateTodo()
        }
    }

    private fun updateTodo() {
        val title = binding.etTitle.text.toString().trim()
        val description = binding.etDescription.text.toString().trim()
        val startDateString = binding.etStartDate.text.toString().trim()
        val dueDateString = binding.etDueDate.text.toString().trim()
        val place = binding.etPlace.text.toString().trim()

        if (title.isEmpty()) {
            binding.tilTitle.error = "Title cannot be empty"
            return
        } else {
            binding.tilTitle.error = null
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

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val startDate: Date
        val dueDate: Date
        try {
            startDate = dateFormat.parse(startDateString) ?: Date()
            dueDate = dateFormat.parse(dueDateString) ?: Date()

            if (dueDate.before(startDate)) {
                binding.tilDueDate.error = "Due Date cannot be before Start Date"
                Toast.makeText(requireContext(), "Due Date must be on or after Start Date.", Toast.LENGTH_LONG).show()
                return
            }

        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Error parsing dates. Please select valid dates.", Toast.LENGTH_LONG).show()
            return
        }

        // Use the currentTodo object to ensure you update the existing entry
        currentTodo?.let { existingTodo ->
            val updatedTodo = existingTodo.copy(
                tittle = title,
                description = if (description.isEmpty()) null else description,
                startDate = startDate,
                dueDate = dueDate,
                place = if (place.isEmpty()) null else place
                // isComplete and completedDate are typically managed in the main list or a dedicated checkbox
            )
            todosViewModel.update(updatedTodo)
            Toast.makeText(requireContext(), "Todo '${updatedTodo.tittle}' updated!", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack() // Go back to the previous fragment (TodosFragment)
        } ?: run {
            Toast.makeText(requireContext(), "Error: Original todo not found.", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}