package com.example.foodfit.di


import com.example.calorietracker.domain.food_tracker.respository.FoodTrackerRepository
import com.example.calorietracker.domain.food_tracker.use_cases.*
import com.example.foodfit.data.datastore.CalorieTrackerDataStore
import com.example.foodfit.domain.food_tracker.use_cases.CalculateMealNutrients
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @ViewModelScoped
    @Provides
    fun provideTrackFoodUseCases(
        dataStore: CalorieTrackerDataStore,
        repository: FoodTrackerRepository
    ): TrackFoodUseCases {
        return TrackFoodUseCases(
            trackFood = TrackFood(repository),
            deleteTrackedFood = DeleteTrackedFood(repository),
            getFoodFromDate = GetFoodFromDate(repository),
            searchFood = SearchFood(repository),
            calculateMealNutrients = CalculateMealNutrients(dataStore)
        )
    }
}