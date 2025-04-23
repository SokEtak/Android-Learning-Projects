package com.example.ktlearning

fun multiply (x: Int , y :Int) :Int{
    return x*y
}
// Define a function modFun that takes two Ints and returns their division result
fun modFun(x:Int , y: Int): Int {
return y/x
}

fun main(args: Array<String>) {

    //no argument and return return nothing
    //simple lambda expression
    val printTheText : () -> Unit = {println("Hello Text")}

    //way1
    //2 params and return the Int value
    val sum : (Int,Int) -> Int = {x,y -> x+y} // x+y is return
    val add = sum(12,8)
    println(add)

    //way2(the same as way1)
    //val sum = {x:Int , y:Int -> x+y}

    //1 argument and return int
    //when single parameter u can reference it by  "it"
    val square :(Int)->Int = {
        println(it.toString())
        it*it} // last expression is returned
    println(square(5).toString())

    //lambda that accept 2 param of type int and return int
    //first param assign to x , second assign to y
    //and perform the action of calling multiply by passing x,y
    val mul : (Int,Int) -> Int = {x,y -> multiply(x,y)} //return  function
    println(mul(3,4))


    // Define a lambda `mod` that accepts a function of type (Int, Int) , and 2 Int param and return int
    val mod: ((Int, Int) -> Int ,Int,Int) -> Int = { modOperation,x,y ->
        // Call the passed function (modFunction) with specific arguments
        modOperation(x,y) //return modOperation(x,y) to where it call
    }

    // call mod and pass 3 param:modFun,12,4
    val result = mod(::modFun,12,4) // modFun wil pass to modOperation(in lambda) , 12 assign to x , 4 assign to y then assign it to result
    println(result) //print the result

}