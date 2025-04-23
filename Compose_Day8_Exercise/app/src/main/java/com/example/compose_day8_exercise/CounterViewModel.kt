package com.example.compose_day8_exercise


import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel


class CounterViewModel : ViewModel(){
    // the _ is marked that variable is private
    private val _count =  mutableStateOf(0)
    val count : State<Int> = _count


    fun increment(){
    _count.value+=1
    }

    fun decrement(){
    _count.value-=1
    }

}


