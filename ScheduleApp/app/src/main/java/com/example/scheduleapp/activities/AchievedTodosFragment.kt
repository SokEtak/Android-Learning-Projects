package com.example.scheduleapp.activities

import AchievedTodosAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scheduleapp.MyApp
import com.example.scheduleapp.databinding.FragmentAchievedTodosBinding
import com.example.scheduleapp.viewModels.AchievedTodosViewModel
import com.example.scheduleapp.viewModels.TodosViewModel
import com.example.scheduleapp.viewModels.TodosViewModelFactory

class AchievedTodosFragment : Fragment() {

    private var _binding: FragmentAchievedTodosBinding? = null
    private val binding get() = _binding!!

    private lateinit var achievedViewModel: AchievedTodosViewModel
    private lateinit var todosViewModel: TodosViewModel
    private lateinit var adapter: AchievedTodosAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAchievedTodosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModels()
        setupRecyclerView()
        observeAchievedTodos()
        setupSwipeToDelete()
    }

    private fun initViewModels() {
        val factory = TodosViewModelFactory((requireActivity().application as MyApp).todosRepository)
        todosViewModel = ViewModelProvider(this, factory)[TodosViewModel::class.java]
        achievedViewModel = ViewModelProvider(this, factory)[AchievedTodosViewModel::class.java]
    }

    private fun setupRecyclerView() {
        adapter = AchievedTodosAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun observeAchievedTodos() {
        achievedViewModel.allAchievedTodos.observe(viewLifecycleOwner) { todos ->
            adapter.updateList(todos)
        }
    }

    private fun setupSwipeToDelete() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val todo = adapter.currentItems[viewHolder.bindingAdapterPosition]
//                todosViewModel.delete(todo)
                Log.d("TODO_DEBUG", "----------------------------")
                Log.d("TODO_DEBUG", "ID: ${todo.id}")
                Log.d("TODO_DEBUG", "Title: ${todo.tittle}")
                Log.d("TODO_DEBUG", "Description: ${todo.description}")
                Log.d("TODO_DEBUG", "Start Date: ${todo.startDate}")
                Log.d("TODO_DEBUG", "Due Date: ${todo.dueDate}")
                Log.d("TODO_DEBUG", "Is Complete: ${todo.isComplete}")
                Log.d("TODO_DEBUG", "Place: ${todo.place}")
                Log.d("TODO_DEBUG", "Completed Date: ${todo.completedDate}")
                Log.d("TODO_DEBUG", "----------------------------")

//                Toast.makeText(requireContext(), "Deleted achieved todo: ${todo.title}", Toast.LENGTH_SHORT).show()
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.recyclerView)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
