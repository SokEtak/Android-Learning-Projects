package com.example.scheduleapp.fragments

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.ColorDrawable
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
import com.example.scheduleapp.R // Make sure R is imported for drawable and color resources
import com.example.scheduleapp.activities.AddTodoActivity
import com.example.scheduleapp.adapters.TodosAdapter
import com.example.scheduleapp.databinding.FragmentTodosBinding
import com.example.scheduleapp.models.Todos
import com.example.scheduleapp.viewModels.TodosViewModel
import com.example.scheduleapp.viewModels.TodosViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

class TodosFragment : Fragment() {

    private var _binding: FragmentTodosBinding? = null
    private val binding get() = _binding!!

    private lateinit var todosViewModel: TodosViewModel
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
        setupItemTouchHelper() // New function to set up swipe gestures

        // Optional: Populate sample data if your database is empty for initial run
        // You might want to do this from your ViewModel/Repository on first app launch only.
        // populateSampleData()
    }

    private fun initViewModel() {
        val todosViewModelFactory = TodosViewModelFactory((requireActivity().application as MyApp).todosRepository)
        todosViewModel = ViewModelProvider(this, todosViewModelFactory)[TodosViewModel::class.java]
    }

    private fun setupRecyclerView() {
        todoAdapter = TodosAdapter(
            context = requireContext(),
            onTodoCheckedChange = { todo ->
                todosViewModel.update(todo)
            },
            onItemLongClick = {},
            onItemClick = {}
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
                // Not used for swipe-to-delete/achieve functionality
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                // Get the actual item from the adapter's current list (which might be filtered)
                val swipedTodo = todoAdapter.currentTodosList[position]

                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        // Swipe left to delete
                        todosViewModel.delete(swipedTodo)
                        Toast.makeText(requireContext(), "Todo '${swipedTodo.tittle}' deleted!", Toast.LENGTH_SHORT).show()
                    }
                    ItemTouchHelper.RIGHT -> {
                        // Swipe right to mark as achieved/complete
                        val updatedTodo = swipedTodo.copy(isComplete = true, completedDate = Date())
                        todosViewModel.update(updatedTodo)
                        Toast.makeText(requireContext(), "Todo '${swipedTodo.tittle}' marked as complete!", Toast.LENGTH_SHORT).show()
                    }
                }
                // No need to manually notify adapter here if LiveData observer is set up
                // as the ViewModel operation will trigger a LiveData update, which
                // will then call todoAdapter.setTodos() and handle DiffUtil.
            }

            // Optional: Add visual feedback during swipe
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
                    paint.color = ContextCompat.getColor(context, R.color.light_green)

                    val backgroundRect = RectF(
                        itemView.left.toFloat(),
                        backgroundTop.toFloat(),
                        itemView.left + swipeDistance,
                        backgroundBottom.toFloat()
                    )
                    c.drawRoundRect(backgroundRect, cornerRadius, cornerRadius, paint)

                    completeIcon?.let {
                        val progress = swipeDistance / itemWidth
                        val iconLeft = itemView.left + (progress * itemWidth).toInt() - iconSize - 32
                        val iconTop = itemView.top + (itemView.height - iconSize) / 2
                        val iconRight = iconLeft + iconSize
                        val iconBottom = iconTop + iconSize

                        it.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                        it.draw(c)
                    }

                } else if (dX < 0) {
                    // Swipe Left → Delete
                    paint.color = ContextCompat.getColor(context, R.color.light_red)

                    val backgroundRect = RectF(
                        itemView.right + swipeDistance,
                        backgroundTop.toFloat(),
                        itemView.right.toFloat(),
                        backgroundBottom.toFloat()
                    )
                    c.drawRoundRect(backgroundRect, cornerRadius, cornerRadius, paint)

                    deleteIcon?.let {
                        val progress = -swipeDistance / itemWidth
                        val iconRight = itemView.right - (progress * itemWidth).toInt() + iconSize + 32
                        val iconLeft = iconRight - iconSize
                        val iconTop = itemView.top + (itemView.height - iconSize) / 2
                        val iconBottom = iconTop + iconSize

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

    // You should move this `populateSampleData` logic to your ViewModel or Repository
    // and ideally run it only once if the database is empty, not on every app launch.
    private fun populateSampleData() {
        val calendar = Calendar.getInstance()
        calendar.timeZone = TimeZone.getTimeZone("Asia/Phnom_Penh")

        fun createDate(year: Int, month: Int, day: Int, hour: Int = 0, minute: Int = 0, second: Int = 0): Date {
            calendar.set(year, month - 1, day, hour, minute, second)
            calendar.set(Calendar.MILLISECOND, 0)
            return calendar.time
        }

        todosViewModel.insert(Todos(0, "Buy Groceries", "Milk, eggs, bread, cheese",
            createDate(2025, 6, 10, 9, 0), createDate(2025, 6, 15, 17, 0), false, "Supermarket", Date()))
        todosViewModel.insert(Todos(0, "Walk the Dog", "Morning walk in the park",
            createDate(2025, 6, 11, 7, 30), createDate(2025, 6, 11, 8, 0), true, "Park", createDate(2025, 6, 11, 8, 0)))
        todosViewModel.insert(Todos(0, "Call Mom", "Wish her happy birthday",
            createDate(2025, 6, 12, 10, 0), createDate(2025, 6, 13, 19, 0), false, "Home", Date()))
        todosViewModel.insert(Todos(0, "Meeting with John", "Project discussion at 2 PM",
            createDate(2025, 6, 14, 14, 0), createDate(2025, 6, 14, 15, 0), false, "Office", Date()))
        todosViewModel.insert(Todos(0, "Read Book", "Finish 'The Great Gatsby'",
            createDate(2025, 6, 1, 20, 0), createDate(2025, 6, 20, 22, 0), false, "Home", Date()))
        todosViewModel.insert(Todos(0, "Pay Bills", "Electricity and internet bills due",
            createDate(2025, 5, 25, 9, 0), createDate(2025, 6, 1, 23, 59), true, "Online", createDate(2025, 6, 1, 10, 0)))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clean up the binding when the view is destroyed
    }
}