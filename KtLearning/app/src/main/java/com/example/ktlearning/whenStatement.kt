package com.example.ktlearning
fun checkNum(n : Int) : String {
    return when {
        n<0 ->"negative number"
        n>0 ->"Positive number"
        else->"Zero"
    }
}

fun main(args: Array<String>) {
    val numToWord : Int = 2

    when(numToWord)
    {
        1 -> println("one")
        2 -> println("two")
        3 -> println("three")
        4 -> println("four")
        5 -> println("five")
        else-> print("out of range")
    }
    val word : String = "Three"
    val wordToNumber  : Int? = when(word.uppercase()){
        "ONE" -> 1
        "TWO" -> 2
        "THREE" -> 3
        "FOUR" -> 4
        "FIVE" -> 5
        else-> null
    }

    println(wordToNumber)
    val rangeVal = 20 until  30
    val range : String = when(rangeVal){
        1..10 ->"1-10"
        11 until  20->"10-20"
        20 until  30->"20-30"
        31..40 ->"30-40"
        41..50 ->"40-50"
        else -> "out of range"
    }
    println(range)

    val guess = 1

    var checkTheType = when(guess){
        is Int -> "Int"
        else -> "That not the int number"
    }

    val cats = 0
    val dogs = 0

    val judgement = when {
        cats < dogs -> "U are a dog lover"
        cats > dogs -> "U are a cat lover"
        cats == 1 && dogs ==1-> "U are both cat and dog lover"
        else->"u don't like the cat or dog"
    }
    println(judgement)

    println(checkNum(3))
}