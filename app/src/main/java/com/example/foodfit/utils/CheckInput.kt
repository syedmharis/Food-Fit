package com.example.foodfit.utils

object CheckInput {
    fun isValid(input: String): Boolean {
        val number = input.toIntOrNull()
        return number != null && number in 1..99
    }
}