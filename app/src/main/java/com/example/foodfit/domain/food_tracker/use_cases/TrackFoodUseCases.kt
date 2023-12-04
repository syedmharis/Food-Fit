package com.example.calorietracker.domain.food_tracker.use_cases

import com.example.foodfit.domain.food_tracker.use_cases.CalculateMealNutrients

data class TrackFoodUseCases(
    val trackFood: TrackFood,
    val deleteTrackedFood: DeleteTrackedFood,
    val searchFood: SearchFood,
    val getFoodFromDate: GetFoodFromDate,
    val calculateMealNutrients: CalculateMealNutrients
)