package com.example.ktlearning

import android.os.health.HealthStats

interface InsideTheBodyAction{
     var isGoodMentalHearth :Boolean?

    fun sing(){
        println("The alive is singing")
    }
    abstract fun thinking()
}

abstract class Action constructor(
     private var name :String?,
     health:Int?
)
{
    protected open var healthStats : Int? = health
        get() {return field}
        set(value){field=value}

    abstract fun walking()
    abstract fun running()
    abstract fun eating()
    open override fun toString(): String {
        return "$name/$healthStats"
    }
}
class Human constructor(name: String?,health: Int?) : Action(name,health) {
    override fun walking() {
        println("Human is walking")
    }

    override fun running() {
        println("Human is running")
    }

    override fun eating() {
        println("Human is eating")
    }

    override fun toString(): String {
        return super.toString()
    }

}

class Pet constructor(name: String?,health: Int?):Action(name,health){
    override fun walking() {
         println("The pet is eating")
    }

    override fun running() {
         println("The pet is eating")
    }

    override fun eating() {
         println("The pet is eating")
    }
    override fun toString(): String {
        return super.toString()
    }

}
fun main(args: Array<String>) {
val pet1 = Pet("pet1",45)
val human1 = Human("human1",66)
    println(pet1.toString())
    println(human1.toString())
    println(human1.)
}