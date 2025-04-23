package com.example.ktlearning

//primary constructor
open class Animal(//secondary constructor(similar to every constructor in java)
    open var name: String, open var age: Int?, var isMammal: Boolean = false)
{
    var petPoint : String? = null
        get(){return field }
        set(value) {
            field = value
        }

    open fun makeSound(animalSound:String="Animal sound"){
        println(animalSound)
    }
    fun makeSound(){}
}
class Cat(override var name: String, override var age: Int?, isMammal: Boolean): Animal(name,age,isMammal) {

    override fun makeSound(animalSound: String) {
        super.makeSound("Meow Meow") //call method inside the super class method
    }

    override fun toString(): String {
        return "$name/$age/$isMammal"
    }
}


fun main(args: Array<String>) {
    val cat = Animal("Tori",2,true)
    println(cat.name)
    cat.age = 3
    println(cat.age)
    println(cat.isMammal)

    val cat2= Cat("Gojo",1,true)
    val cat3= Cat("Koki",2,false)
    println(cat2.toString())
    println(cat3.toString())
}