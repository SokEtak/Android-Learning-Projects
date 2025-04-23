package com.example.myapplication.Inheritance

fun main(args: Array<String>) {
    var car1 = Car("lambogini",400000.0,200)
    var car2 = Car("Rollroy",300000.0,160)

//    println(car1.type)
//    println(car1.model)
//    println(car1.price)
//    println(car1.maxSpeed)
//
    println(car2.type)
    println(car2.model)
    println(car2.price)
    println(car2.maxSpeed)

  car1.start()
}