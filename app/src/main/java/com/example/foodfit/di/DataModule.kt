import android.app.Application
import com.example.foodfit.data.local.FoodDatabase
import com.example.foodfit.data.remote.CalorieApi
import com.example.foodfit.data.remote.CalorieApi.Companion.BASE_URL
import com.example.foodfit.domain.food_tracker.respository.FoodTrackerRepository
import com.example.foodfit.domain.food_tracker.respository.FoodTrackerRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
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
    fun provideTrackerDatabase(application: Application): FoodDatabase {
        return androidx.room.Room.databaseBuilder(
            application,
            FoodDatabase::class.java,
            "food_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideTrackerFoodRepository(foodDatabase: FoodDatabase): FoodTrackerRepository {
        return FoodTrackerRepositoryImpl(foodDatabase)
    }
}
