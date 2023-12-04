package com.example.foodfit.domain.on_boarding.model


sealed class GoalType(val name: String) {

    object LoseWeight: GoalType("lose_weight")
    object KeepWeight: GoalType("keep_weight")
    object GainWeight: GoalType("gain_weight")

    companion object{
        fun fromString(goalType:String): GoalType {
            return when(goalType){
                "lose_weight"-> LoseWeight
                "keep_weight"-> KeepWeight
                "gain_weight"-> GainWeight
                else-> KeepWeight
            }
        }
    }

}