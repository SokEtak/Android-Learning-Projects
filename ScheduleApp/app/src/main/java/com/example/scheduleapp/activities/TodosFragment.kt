package com.example.scheduleapp.fragments

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scheduleapp.MyApp
import com.example.scheduleapp.R
import com.example.scheduleapp.activities.AddTodoActivity
import com.example.scheduleapp.activities.UpdateTodoFragment
import com.example.scheduleapp.adapters.TodosAdapter
import com.example.scheduleapp.databinding.FragmentTodosBinding
import com.example.scheduleapp.models.Todo
import com.example.scheduleapp.viewModels.AchievedTodosViewModel
import com.example.scheduleapp.viewModels.TodosViewModel
import com.example.scheduleapp.viewModels.TodosViewModelFactory
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

class TodosFragment : Fragment() {

    private var _binding: FragmentTodosBinding? = null
    private val binding get() = _binding!!

    private lateinit var todosViewModel: TodosViewModel
    private lateinit var achievedTodosViewModel: AchievedTodosViewModel
    private lateinit var todoAdapter: TodosAdapter

    private var currentSearchQuery: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        setupRecyclerView()
        observeTodosLiveData()
        setupFabAddButton()
        setupSearchView()
        setupItemTouchHelper()

        // Optional: Populate sample data if your database is empty for initial run
        // You might want to do this from your ViewModel/Repository on first app launch only.
         populateSampleData()
    }

    private fun initViewModel() {
        val todosViewModelFactory = TodosViewModelFactory((requireActivity().application as MyApp).todosRepository)
        todosViewModel = ViewModelProvider(this, todosViewModelFactory)[TodosViewModel::class.java]
    }

    private fun setupRecyclerView() {
        // Correctly pass all three lambda functions to the adapter constructor
        todoAdapter = TodosAdapter(
            context = requireContext(),
            onTodoCheckedChange = { todo ->
                todosViewModel.update(todo)
            },
            onItemLongClick = { todo ->
                // Keep your long click logic here if any, or remove the lambda if not needed
                Toast.makeText(requireContext(), "Long clicked: ${todo.tittle}", Toast.LENGTH_SHORT).show()
            },
            onItemClick = { todo ->
                // NEW: Handle item click to navigate to UpdateTodoFragment
                val updateFragment = UpdateTodoFragment.newInstance(todo.id)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, updateFragment) // R.id.fragment_container is the ID of your FrameLayout/FragmentContainerView in MainActivity
                    .addToBackStack(null) // Add to back stack to allow going back with the back button
                    .commit()
            }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager (requireContext())
        binding.recyclerView.adapter = todoAdapter
    }

    private fun observeTodosLiveData() {
        todosViewModel.allTodos.observe(viewLifecycleOwner) { todos ->
            todoAdapter.setTodos(todos, currentSearchQuery)
        }
    }

    private fun setupFabAddButton() {
        binding.fabAdd.setOnClickListener {
            val intent = Intent(requireContext(), AddTodoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                currentSearchQuery = query
                todoAdapter.filterTodos(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                currentSearchQuery = newText
                todoAdapter.filterTodos(newText)
                return true
            }
        })
    }

    private fun setupItemTouchHelper() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0, // We don't support drag-and-drop
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT // Enable swiping left and right
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val swipedTodo = todoAdapter.currentTodosList[position]

                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        todosViewModel.delete(swipedTodo)
                        Toast.makeText(requireContext(), "Todo '${swipedTodo.tittle}' deleted!", Toast.LENGTH_SHORT).show()
                    }
                    ItemTouchHelper.RIGHT -> {

                        val updatedTodo = swipedTodo.copy(isComplete = true, completedDate = Date())
                        todosViewModel.update(updatedTodo)

                        Toast.makeText(requireContext(), "Todo '${swipedTodo.tittle}' marked as complete!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                val context = requireContext()

                val iconSize = (itemView.height * 0.4).toInt()
                val backgroundHeight = (itemView.height * 0.6).toInt()
                val backgroundTop = itemView.top + (itemView.height - backgroundHeight) / 2
                val backgroundBottom = backgroundTop + backgroundHeight
                val cornerRadius = 20f

                val deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_delete_24)
                val completeIcon = ContextCompat.getDrawable(context, R.drawable.ic_check_circle_24)

                val paint = Paint().apply {
                    isAntiAlias = true
                }

                val itemWidth = itemView.width
                val swipeDistance = dX.coerceIn(-itemWidth.toFloat(), itemWidth.toFloat())

                if (dX > 0) {
                    // Swipe Right → Complete
                    paint.color = ContextCompat.getColor(context, R.color.green_achieved) // Use green for achieve

                    val backgroundRect = RectF(
                        itemView.left.toFloat(),
                        backgroundTop.toFloat(),
                        itemView.left + swipeDistance,
                        backgroundBottom.toFloat()
                    )
                    c.drawRoundRect(backgroundRect, cornerRadius, cornerRadius, paint)

                    completeIcon?.let {
                        val iconMargin = (backgroundHeight - it.intrinsicHeight) / 2
                        val iconLeft = itemView.left + iconMargin
                        val iconTop = backgroundTop + iconMargin
                        val iconRight = itemView.left + iconMargin + it.intrinsicWidth
                        val iconBottom = backgroundTop + iconMargin + it.intrinsicHeight
                        it.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                        it.draw(c)
                    }

                } else if (dX < 0) {
                    // Swipe Left → Delete
                    paint.color = ContextCompat.getColor(context, R.color.red_delete) // Use red for delete

                    val backgroundRect = RectF(
                        itemView.right + swipeDistance,
                        backgroundTop.toFloat(),
                        itemView.right.toFloat(),
                        backgroundBottom.toFloat()
                    )
                    c.drawRoundRect(backgroundRect, cornerRadius, cornerRadius, paint)

                    deleteIcon?.let {
                        val iconMargin = (backgroundHeight - it.intrinsicHeight) / 2
                        val iconRight = itemView.right - iconMargin
                        val iconLeft = itemView.right - iconMargin - it.intrinsicWidth
                        val iconTop = backgroundTop + iconMargin
                        val iconBottom = backgroundTop + iconMargin + it.intrinsicHeight
                        it.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                        it.draw(c)
                    }
                }

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun populateSampleData() {
        val calendar = Calendar.getInstance()
        calendar.timeZone = TimeZone.getTimeZone("Asia/Phnom_Penh")

        fun createDate(year: Int, month: Int, day: Int, hour: Int = 0, minute: Int = 0, second: Int = 0): Date {
            calendar.set(year, month - 1, day, hour, minute, second)
            calendar.set(Calendar.MILLISECOND, 0)
            return calendar.time
        }

//        todosViewModel.insert(Todo(0, "Buy Groceries", "Milk, eggs, bread, cheese",
//            createDate(2025, 6, 10, 9, 0), createDate(2025, 6, 15, 17, 0), false, "Supermarket", Date()))
//        todosViewModel.insert(Todo(0, "Walk the Dog", "Morning walk in the park",
//            createDate(2025, 6, 11, 7, 30), createDate(2025, 6, 11, 8, 0), true, "Park", createDate(2025, 6, 11, 8, 0)))
//        todosViewModel.insert(Todo(0, "Call Mom", "Wish her happy birthday",
//            createDate(2025, 6, 12, 10, 0), createDate(2025, 6, 13, 19, 0), false, "Home", Date()))
//        todosViewModel.insert(Todo(0, "Meeting with John", "Project discussion at 2 PM",
//            createDate(2025, 6, 14, 14, 0), createDate(2025, 6, 14, 15, 0), false, "Office", Date()))
//        todosViewModel.insert(Todo(0, "Read Book", "Finish 'The Great Gatsby'",
//            createDate(2025, 6, 1, 20, 0), createDate(2025, 6, 20, 22, 0), false, "Home", Date()))
//        todosViewModel.insert(Todo(0, "Pay Bills", "Electricity and internet bills due",
//            createDate(2025, 5, 25, 9, 0), createDate(2025, 6, 1, 23, 59), true, "Online", createDate(2025, 6, 1, 10, 0)))
//        todosViewModel.insert(Todo(0, "Buy Groceries", "Milk, eggs, bread, cheese",
//            createDate(2025, 6, 10, 9, 0), createDate(2025, 6, 15, 17, 0), false, "Supermarket", Date()))
//        todosViewModel.insert(Todo(0, "Walk the Dog", "Morning walk in the park",
//            createDate(2025, 6, 11, 7, 30), createDate(2025, 6, 11, 8, 0), true, "Park", createDate(2025, 6, 11, 8, 0)))
//        todosViewModel.insert(Todo(0, "Call Mom", "Wish her happy birthday",
//            createDate(2025, 6, 12, 10, 0), createDate(2025, 6, 13, 19, 0), false, "Home", Date()))
//        todosViewModel.insert(Todo(0, "Meeting with John", "Project discussion at 2 PM",
//            createDate(2025, 6, 14, 14, 0), createDate(2025, 6, 14, 15, 0), false, "Office", Date()))
//        todosViewModel.insert(Todo(0, "Read Book", "Finish 'The Great Gatsby'",
//            createDate(2025, 6, 1, 20, 0), createDate(2025, 6, 20, 22, 0), false, "Home", Date()))
//        todosViewModel.insert(Todo(0, "Pay Bills", "Electricity and internet bills due",
//            createDate(2025, 5, 25, 9, 0), createDate(2025, 6, 1, 23, 59), true, "Online", createDate(2025, 6, 1, 10, 0)))
//        todosViewModel.insert(Todo(0, "Dentist Appointment", "Check-up and cleaning",
//            createDate(2025, 6, 17, 10, 0), createDate(2025, 6, 17, 11, 0), false, "Dentist's Office", Date()))
//        todosViewModel.insert(Todo(0, "Workout", "Gym session - cardio and weights",
//            createDate(2025, 6, 16, 18, 0), createDate(2025, 6, 16, 19, 0), false, "Gym", Date()))
//        todosViewModel.insert(Todo(0, "Plan Weekend Trip", "Research destinations and book accommodation",
//            createDate(2025, 6, 15, 11, 0), createDate(2025, 6, 22, 17, 0), false, "Home", Date()))
//        todosViewModel.insert(Todo(0, "Send Emails", "Reply to pending work emails",
//            createDate(2025, 6, 15, 9, 0), createDate(2025, 6, 15, 12, 0), false, "Office", Date()))
//        todosViewModel.insert(Todo(0, "Car Maintenance", "Oil change and tire rotation",
//            createDate(2025, 6, 19, 14, 0), createDate(2025, 6, 19, 16, 0), false, "Auto Shop", Date()))
//        todosViewModel.insert(Todo(0, "Prepare Presentation", "Slides for Monday's meeting",
//            createDate(2025, 6, 15, 13, 0), createDate(2025, 6, 16, 9, 0), false, "Home", Date()))
//        todosViewModel.insert(Todo(0, "Grocery Shopping", "Weekly groceries list",
//            createDate(2025, 6, 21, 9, 0), createDate(2025, 6, 21, 10, 30), false, "Supermarket", Date()))
//        todosViewModel.insert(Todo(0, "Laundry", "Wash and fold clothes",
//            createDate(2025, 6, 15, 16, 0), createDate(2025, 6, 15, 17, 0), true, "Home", createDate(2025, 6, 15, 16, 45)))
//        todosViewModel.insert(Todo(0, "Water Plants", "Check all indoor plants",
//            createDate(2025, 6, 15, 8, 0), createDate(2025, 6, 15, 8, 15), false, "Home", Date()))
//        todosViewModel.insert(Todo(0, "Research New Phone", "Compare models and features",
//
//            createDate(2025, 6, 18, 19, 0), createDate(2025, 6, 20, 21, 0), false, "Home", Date()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}