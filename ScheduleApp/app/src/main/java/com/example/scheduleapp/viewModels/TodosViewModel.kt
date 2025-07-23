package com.example.scheduleapp.viewModels

import androidx.lifecycle.*
import com.example.scheduleapp.models.Todo
import com.example.scheduleapp.repositories.TodosRepository
import kotlinx.coroutines.launch

class TodosViewModel(private val todoRepository: TodosRepository) : ViewModel() {

    val allTodos: LiveData<List<Todo>> = todoRepository.allTodos.asLiveData()

    fun insert(todo: Todo) = viewModelScope.launch {
        todoRepository.insert(todo)
    }

    fun update(todo: Todo) = viewModelScope.launch {
        todoRepository.update(todo)
    }

    fun delete(todo: Todo) = viewModelScope.launch {
        todoRepository.delete(todo)
    }

    fun deleteAll() = viewModelScope.launch {
        todoRepository.deleteAll()
    }

    fun getTodoById(id: Int): LiveData<Todo> {
        return todoRepository.getTodoById(id)
    }
}

class TodosViewModelFactory(
    private val repository: TodosRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TodosViewModel::class.java) -> {
                TodosViewModel(repository) as T
            }
            modelClass.isAssignableFrom(AchievedTodosViewModel::class.java) -> {
                AchievedTodosViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

