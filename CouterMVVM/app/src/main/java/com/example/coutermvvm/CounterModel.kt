package com.example.coutermvvm

data class CounterModel(var count : Int)

class CounterRepository{
    private val _counter = CounterModel(0)

    fun getCounter() = _counter

    fun incrementCounter(){_counter.count++}
    fun decrementCounter(){_counter.count--}
}