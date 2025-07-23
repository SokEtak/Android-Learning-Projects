package com.example.scheduleapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.scheduleapp.databinding.TodosItemBinding
import com.example.scheduleapp.models.Todo
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TodosAdapter(
    private val context: Context,
    private val onTodoCheckedChange: (Todo) -> Unit, // Existing checkbox listener
    private val onItemLongClick: (Todo) -> Unit,    // Existing long click listener
    private val onItemClick: (Todo) -> Unit         // NEW: Item click listener
) : RecyclerView.Adapter<TodosAdapter.TodoViewHolder>() {

    var allTodosUnfiltered: List<Todo> = emptyList()
    var currentTodosList: List<Todo> = emptyList()

    inner class TodoViewHolder(private val binding: TodosItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.tvTitle.text = todo.tittle
            binding.tvDescription.text = todo.description
            binding.tvDueDate.text = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(todo.dueDate)
            binding.tvPlace.text = todo.place

            binding.cbIsComplete.isChecked = todo.isComplete
            binding.cbIsComplete.setOnCheckedChangeListener { _, isChecked ->
                val updatedTodo = todo.copy(isComplete = isChecked, completedDate = if (isChecked) Date() else null)
                onTodoCheckedChange(updatedTodo)
            }

            // Set the new click listener for the entire item view
            binding.root.setOnClickListener {
                onItemClick(todo)
            }

            binding.root.setOnLongClickListener {
                onItemLongClick(todo)
                true // Consume the long click event
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = TodosItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(currentTodosList[position])
    }

    override fun getItemCount(): Int = currentTodosList.size

    fun setTodos(todos: List<Todo>, query: String?) {
        allTodosUnfiltered = todos
        filterTodos(query)
    }

    fun filterTodos(query: String?) {
        val filteredList = if (query.isNullOrBlank()) {
            allTodosUnfiltered
        } else {
            allTodosUnfiltered.filter {
                it.tittle?.contains(query, ignoreCase = true) == true ||
                        (it.description?.contains(query, ignoreCase = true) == true) ||
                            (it.place?.contains(query, ignoreCase = true) == true)
            }
        }

        val diffResult = DiffUtil.calculateDiff(TodoDiffCallback(currentTodosList, filteredList))
        currentTodosList = filteredList
        diffResult.dispatchUpdatesTo(this)
    }

    private class TodoDiffCallback(
        private val oldList: List<Todo>,
        private val newList: List<Todo>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}