package com.example.ktlearning

fun main(args: Array<String>) {
    //set is similar to list , but cannot duplicate the item(remove duplicated)
    val immutableSet  = setOf("Khmer","Khmer","Us","China","Khmer","China","Thai")
    println(immutableSet)

    var mutableSet = mutableSetOf("khmer","khmer","khmer","China","malay","China")
    mutableSet.add("Thai")
    mutableSet.add("Thai")
    println(mutableSet)

}