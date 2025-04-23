package com.example.myapplication

import android.annotation.SuppressLint
import java.util.Date
import java.text.SimpleDateFormat

open class Car {
    var model: String? = null
    var brand: String? = null
    var year: Int? = null
    var price: Double? = null

    open fun infoDisplay(): String {
        return "Model: ${this.model}, Brand: ${this.brand}, Year: ${this.year}, Price: ${this.price}"
    }
}

class Person : Car() {
    var name: String? = null
    var age: Int? = null
    var gender: Char? = null
    var dob: Date? = null

    @SuppressLint("SimpleDateFormat")
    override fun infoDisplay(): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd")
        return "${this.name}, Age: ${this.age}, Gender: ${this.gender}, DOB: ${
            dob?.let {
                dateFormat.format(
                    it
                )
            }
        }"
    }
}

// constructor test
class Animal constructor(var name: String, age: Int, dob: String, gender: Char, habitat: String) {
    init {
        println("$name:${age}:${gender}:${dob}:${habitat}")
    }
}

@SuppressLint("SimpleDateFormat")
fun main(args: Array<String>) {
    val dateFormat = SimpleDateFormat("yyyy/MM/dd")
    // Create an instance of Person
    val person1 = Person()
    person1.name = "July"
    person1.age = 32
    person1.gender = 'f'
    // Parsing the date string into a Date object
    person1.dob = dateFormat.parse("2000/12/12")

    // Display person info
    println(person1.infoDisplay())

    // Create an instance of Car
    val car1 = Car()
    car1.model = "Lambo"
    car1.brand = "lambogini"
    car1.year = 2024
    car1.price = 400000.00
    // You can set car properties here if you define them and create setter methods or a constructor.
    println(car1.infoDisplay()) // This will print the default values (null)


    var cat = Animal("Joki", 2, "2022/12/9", 'm', "land")
    var fish = Animal("Lolo", 1, "2023/12/9", 'm', "water")
}
