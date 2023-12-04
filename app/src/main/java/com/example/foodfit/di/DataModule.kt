package com.example.foodfit.di

import android.app.Application
import com.example.calorietracker.domain.food_tracker.respository.FoodTrackerRepository
import com.example.foodfit.data.local.FoodDatabase
import com.example.foodfit.data.remote.CalorieApi
import com.example.foodfit.data.remote.CalorieApi.Companion.BASE_URL
import com.example.foodfit.domain.food_tracker.respository.FoodTrackerRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

        @Singleton
        @Provides
        fun provideApi(): CalorieApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CalorieApi::class.java)
        }

    @Provides
    @Singleton
    fun provideTrackerDatabase(app: Application): FoodDatabase {
        return androidx.room.Room.databaseBuilder(
            app,
            FoodDatabase::class.java,
            "food_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideTrackerFoodRepository(
        api: CalorieApi,
        foodDatabase: FoodDatabase
    ):FoodTrackerRepository{
        return FoodTrackerRepositoryImpl(api,foodDatabase)
    }
}