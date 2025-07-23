package com.example.scheduleapp.viewModels

import AchievesTodo
import androidx.lifecycle.*
import com.example.scheduleapp.models.Todo
import com.example.scheduleapp.repositories.TodosRepository


class AchievedTodosViewModel(repository: TodosRepository) : ViewModel() {

    val allAchievedTodos: LiveData<List<Todo>> = repository.allTodos.asLiveData()

//    fun insert(achievesTodo: AchievesTodo) = viewModelScope.launch {
//        achievesTodoRepository.insert(achievesTodo)
//    }

}

//class AchievedTodosViewModelFactory(private val repository: AchievedTodosRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(AchievedTodosViewModel::class.java)) {
//            return AchievedTodosViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}