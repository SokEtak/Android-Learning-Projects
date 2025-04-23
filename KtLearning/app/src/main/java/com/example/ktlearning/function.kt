package com.example.ktlearning

// simple function
//non-return value(Unit)
fun sayHi(){
    println("Hi Programmer")
}
//function with parameter
fun sayHi(name: String) {
    println("Hello $name")
}
//default value parameter
fun greet(name: String = "friend"){
println("Hello $name,how are u")
}
//function with mix  of expression and default value parameter
fun greet(name: String = "friend",  isFine : Boolean = false, breakFast:String="rice"){
    println("Hello $name,how are u?")
    println("Oh are u fine:$isFine")
    println("What is your today breakfast:$breakFast")
}

// parameters and return value(Int) function
//return type is Int
fun sum(x :Int = 0, y : Int = 0) : Int{
    return x+y
}

//single expression(similar to return function)
fun minus(x : Int = 100 , y : Int = 10) = x-y // x-y is return

//function that accept another function as single expression
//mathOperation()  has 2 parameter which  both params type are Int and return Int value(mathOperation(a,b))

fun doMath(mathOperation: (Int,Int) -> Int,a : Int = 0 , b :Int = 0 ) = mathOperation(a,b) // return statement

//function that accept another function and return(function) [not define as single argument]
//squareIt accept one parameter which is Int and return the Int value
fun mySquareFun(squareIt: (Int) -> Int,n :Int):Int{
    return squareIt(n)
}

fun multiplyTheNum (n : Int) : Int{
    return n * n
}

fun main(args: Array<String>) {

    //call simple function
    sayHi()

    //function with parameter
    sayHi("Toki")

    //default value parameter
    greet()//default value
    greet("Toki") // non default value

    //calling function with mix regular expression and default value
    greet("Toki",true,"Noodle Soup")

    //Named argument
    greet(breakFast = "Noodle Soup", isFine = true,name = "Poki" )

    //calling return function and assign to a variable
    val total = sum(12,8)
    println(total)

    //calling the single expression function  and assign it to variable
    val minus = minus(12,8)
    println(minus)

    //function that accept another function(lambdas) as single expression and another 2 params
    println(doMath(::minus,10,2))

    //calling  function that accept another function with return value(function) then assign it to a variable
    //pass the multiplyTheNum() to mySquareFun() and it will assign to squareIt
    val square = mySquareFun(::multiplyTheNum,4)
    println(square)

}