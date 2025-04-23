package com.example.myapplication.Inheritance

class Car constructor(model:String,price :Double ,maxSpeed:Int)  : Vehicle() {

    override var type : String? = "Car"
    override var model : String? = model
    override var price : Double? = price
    override var maxSpeed : Int? = maxSpeed



    override fun start(): String {
        val text = super.start()
        return text
    }

    override fun stop(): String {
        val text = super.start()
        return text
    }

    override fun detail(): String
    {
        return super.detail()
    }


}