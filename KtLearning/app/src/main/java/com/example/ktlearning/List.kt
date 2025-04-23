package com.example.ktlearning

import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun main(args: Array<String>) {
    //immutable list : cannot reassign
//    val immutableList : List <String> = listOf<String>("Angkor","TaProam","palace","Sor Kheng Park") // work the same
    println("Immutable list")
    val immutableList  = listOf<String>("Angkor","TaProam","palace","Sor Kheng Park")
//    immutableList[1] = "Khmer" //error cannot reassign
    println(immutableList.get(0))
    println(immutableList[1])
    println(immutableList.isEmpty())
    println(immutableList.contains("kh"))
    println(immutableList.count())
    println("Mutable list")


    //mutable list with var : can reassign and add/remove
    var mutableList = mutableListOf("BTB","PP","KPS")
    println(mutableList.isEmpty()) // check is empty
    println(mutableList.contains("BTT")) // is contain an element
    println(mutableList)

    //reassign value
    mutableList.set(0,"Poi pet")
    mutableList[1] ="Kandal"


    //add
    mutableList.add(("PV"))
    println(mutableList)
    mutableList.add(("KPT"))
    println(mutableList)

    //remove
    mutableList.remove("PP") // remove item via value
    println(mutableList)
    mutableList.removeAt(0) //remove via index
    println(mutableList)
    println(mutableList.size) //size

    var i :Int?

    for (i in mutableList){
        println(i)
    }
    //mutable list with val : can reassign and add/remove
    val valMutableList = mutableListOf("Khmer","Khmer","Angkor","Taprom")
    println(valMutableList)

    valMutableList.set(0,"Cambodia") // replace
    println(valMutableList)
    valMutableList.add("Bayon") //add
    println(valMutableList)

//    valMutableList = mutableListOf("one") // cannot reassgin the list

}