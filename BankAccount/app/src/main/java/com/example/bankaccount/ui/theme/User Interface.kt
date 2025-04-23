package com.example.bankaccount.ui.theme

fun main(args: Array<String>) {
    val tataa = BankAccount("tataa")
    tataa.deposit(123.00)
    tataa.witDraw(123.00)
    tataa.deposit(12.00)
    tataa.deposit(754.00)
    tataa.deposit(154542.63)
    println(tataa.displayBankHolderInfo())
}