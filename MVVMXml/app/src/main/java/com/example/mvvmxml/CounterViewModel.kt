package com.example.mvvmxml

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _repository = CounterRepository()
    private val _count = MutableLiveData<CounterModel>()
    val count: LiveData<CounterModel> = _count

    init {
        _count.value = _repository.getCounter()
    }

    fun increment() {
        _repository.increaseCounter()
        _count.value = _repository.getCounter()
    }
}
