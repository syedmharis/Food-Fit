package com.example.foodfit.domain.food_tracker.use_cases

import com.example.foodfit.domain.food_tracker.model.TrackedFood
import com.example.foodfit.domain.food_tracker.respository.FoodTrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodFromDate(
    private val foodTrackerRepository: FoodTrackerRepository
) {

    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return foodTrackerRepository.getGoodsFromDate(date)
    }
}