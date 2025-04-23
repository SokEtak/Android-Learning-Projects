package com.example.compose_shoppinglist_last

fun main(args: Array<String>) {
    //nullable
    val str  : String? = "Hella"
    str?.let {
        println(it.toLowerCase())
    }

    println(str)
}