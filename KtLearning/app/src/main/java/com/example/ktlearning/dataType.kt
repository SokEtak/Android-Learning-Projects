package com.example.ktlearning

fun main(args: Array<String>) {
    //will have default type by the value
    var a  = 3 // int
    val PI  = 3.14 // double
    var gender = 'f' // char
    var string = "hello world"  // string
    var arr = emptyArray<String>()
    //assign to prevent from Any type
    var i  :Int = 3 // int
    val d :Double=  .14 // double
    var c :Char= 'f' // char
    var str:String= "hello world"  // String

    //nullable type

    var name :String? = null // nullable
//    var text :String = null // non-nullable
    var length : Int? = name?.length // length if not null
    println(length)
    var length2 : Int = name?.length ?: 0 //length or assign 0
    println(length2)
    var length3 : Int = name?.length ?: return // length or return
    println(length3)
    var length4 :Int =name!!.length //length if not null
    println(length4)

}