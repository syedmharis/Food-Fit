package com.example.foodfit.data.mapper

import com.example.foodfit.data.remote.dto.Product
import com.example.foodfit.domain.food_tracker.model.MealType
import com.example.foodfit.domain.food_tracker.model.TrackedFood
import java.time.LocalDate
import kotlin.math.roundToInt

fun Product.toTrackedFood(): TrackedFood{
    val carbsPer100g = nutriments.carbohydrates100g.roundToInt()
    val proteinPer100g = nutriments.proteins100g.roundToInt()
    val fatPer100g = nutriments.proteins100g.roundToInt()
    val caloriesPer100g = nutriments.energyKcal100g.roundToInt()

    return TrackedFood(
        name = productName,
        carbs = carbsPer100g,
        protein = proteinPer100g,
        fat = fatPer100g,
        calories = caloriesPer100g,
        imageUrl = imageFrontThumbUrl,
        amount = 1,
        date = LocalDate.now(),
        mealType = MealType.BreakFast
    )
}