package com.example.compose_shoppinglist_last

import androidx.compose.ui.graphics.Color

fun main(args: Array<String>) {
    val a = listOf(1,2,3,4)
    println(a.map {it * 2})

//    val lambogini = Car("Black","LamboA+",120000.44)
    val lambogini = Car(model ="LamboA+", price = 10000.44, color = "Black")
    val ferrari = lambogini.copy(price = 1900000.00)
    println(lambogini)
    println(ferrari)
}

data class Car (val color: String , val model: String,
                val price : Double)