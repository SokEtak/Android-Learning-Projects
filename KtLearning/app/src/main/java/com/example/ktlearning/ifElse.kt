package com.example.ktlearning

fun main(args: Array<String>) {
    var isATeen = true

    if(isATeen){
        println("U are a teenager")
    }
    val age = 18
    val isElected = if(age>=18){
        println("u are old enough to be elected")
    } else {
        println("u aren't old enough to be elected")
    }

    val x = 12
    val y = 11
    var isSmaller = if(x<y) x else y

    println("The smaller number is $isSmaller")
}