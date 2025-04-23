package com.example.bankaccount.ui.theme

import java.time.LocalDateTime
import java.util.Date

class BankAccount
    (
    private val userHolder: String, id : Long = 11111) : Machine
{
    private var balance : Double = 0.0

    override fun witDraw(balance: Double) {
        if(balance <= this.balance ){
            this.balance -= balance
        }
        else{
            println("$balance is not enough to withdraw")
        }
    }

    override fun deposit(balance: Double) {
        if (balance> this.balance){
            this.balance += balance
        }else{
            println("Machine cannot work because amount to withdraw is less than 0")
        }
    }

    fun displayBankHolderInfo(){
        println("$userHolder : $$balance")
    }
}