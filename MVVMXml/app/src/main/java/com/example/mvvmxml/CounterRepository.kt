package com.example.mvvmxml

class CounterRepository {
    private val  counter = CounterModel(0)

    fun getCounter() = counter

    fun increaseCounter() {
        counter.count++
    }
}