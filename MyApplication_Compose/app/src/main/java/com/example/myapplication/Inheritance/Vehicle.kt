package com.example.myapplication.Inheritance

import android.graphics.ColorSpace.Model

open class Vehicle{

    public open var type: String? = null
    public open var model: String? = null
    public open var price: Double? = null
    public open var maxSpeed: Int? = null

    open fun start() :String{

        return "$type is moving"

    }

    open fun stop():String{

        return "$type has stop"

    }

    open fun detail() : String{

        return """
            Type: $type
            Model: $model
            Price: $price
            Max Speed: $maxSpeed
        """.trimIndent()
    }

}