package com.example.ktlearning

fun main(args: Array<String>) {
   val l = listOf(1,3,4,2,7) // define a list

    //filter only the even number of a list and take first 3 elements
    println(l.filter { it % 2 != 0}.take(3))
    //sort descending
    println(l.sortedDescending())
    //sum then divide with the size/length of list
    println(l.average())
    //return every element * element (2*2) and take first 3
    println(l.map{it*it}.take(3))

    //print the original element value before transform as and add it to the first element and second the transformed element in a new  list ,
    // this process will continue until the last element
    println(l.flatMap { listOf(it,it+10) })
    //start from 0.0(must be floating type) then sum all the value of a list(1+3+4+2)
    println(l.fold(0.0){acc: Double, i: Int -> acc+i }) //0.0 assign to acc , then acc + 1 (index 0 of list)
    // , so first step acc = 1 , and it will continue until the last element

    //similar to fold (better version)
    println(l.reduce{acc, i ->acc+i})

    //perform an action on every element and return Unit
    l.forEach{ print("${it+it} ")}

   println()

   l.onEach { print("$it ")}

    println()

    //spit into pair of list
   // every element that divide without remainder , assign to even else assign to odd

    val range = 1..100
    val (even,odd) =  range.partition {it % 2 == 0}

    println(even)
    println(odd)

    println(range.min())
    println(range.minBy{-it}) //similar to find the max
    println(range.max())
    println(range.maxBy{-it}) //similar to find the min

    println(range.first()) //first element
    println(range.first{it % 12 == 0}) // first element with satisfied condition

    //count
    println(range.count())
    println(l.count{it % 2 == 0}) // count every element matched satisfied condition

    //sort
    println(l.sorted())
    println(l.sortedBy{it%2})

   val list = listOf(1,1,1,4,3,4,5,6,5,7,1)
   //group by
   println(list.groupBy {it%2}) //group by even or odd number

   println(list.distinct())
   println(list.distinctBy{it%2})

}