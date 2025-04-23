package com.example.rockpaperscisor

import kotlin.random.Random

fun main(args: Array<String>) {
    val choice = arrayOf("Rock","Paper","Scissor")
    var userChoice : Int
    val computerChoice = (1..3).random()

    //promt user to input the choice

    println("let make a choice between 1.Rock 2.Paper 3.Scissor:")
    userChoice = readln().toInt()
    println("You choice:${choice[userChoice-1]}")

    //getComputerChoice
    println(computerChoice)
    println("Computer choice:${choice[computerChoice-1]}")


    //find the winner
    if (userChoice == computerChoice){
        println("It's a draw")
    }
    else if(userChoice>computerChoice){
        println("U win")
    }
    else{
        println("U lose")
    }
}