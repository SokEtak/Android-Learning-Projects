package com.example.ktlearning

fun main(args: Array<String>) {
    //immutableMap
    println("ImmutableMap")
    val immutableMap = mapOf("name" to "Tatiko",
                             "age" to 18 ,
                             "grade" to 12)

    println(immutableMap.keys) //print keys
    println(immutableMap.values)//print values
    println(immutableMap.containsKey("name")) //is contain a key
    println(immutableMap.containsValue(18)) //is contain a value
    println(immutableMap.isNullOrEmpty()) // check is null or empty
//  immutableMap["name"] = "Joki"  // cannot reassign or update
//  immutableMap["name"] = "Joki"  // cannot reassign or update
    println(immutableMap)

    for ((k,v) in immutableMap){
        println("$k:$v")
    }

    //mutbleMap
    var mutableMap  = mutableMapOf<String,String>("name" to "Toki","gender" to "male")
    println(mutableMap)
//    mutableMap.keys["name"] = "koki" //error
    mutableMap["name"] = "koki" //error
    mutableMap["position"]="IT" // add
    println(mutableMap)
    mutableMap.remove("position") //remove via key
    mutableMap.remove("gender","male") // remove via key , value
    println(mutableMap)
}