package com.example.foodfit.data.datastore

import android.content.Context
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.foodfit.domain.on_boarding.model.ActivityLevel
import com.example.foodfit.domain.on_boarding.model.Gender
import com.example.foodfit.domain.on_boarding.model.GoalType
import com.example.foodfit.domain.on_boarding.model.UserInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CalorieTrackerDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preference")
        private lateinit var ds: DataStore<Preferences>
        fun dataStore(context: Context): DataStore<Preferences> {
            if (::ds.isInitialized.not()) {
                ds = context.dataStore
            }
            return ds
        }

    }


    private object PreferenceKeys {
        val KEY_ON_BOARDING = booleanPreferencesKey(name = "on_boarding_done")
        val KEY_GENDER = stringPreferencesKey(name = "gender")
        val KEY_AGE = intPreferencesKey(name = "age")
        val KEY_WEIGHT = intPreferencesKey(name = "weight")
        val KEY_HEIGHT = intPreferencesKey(name = "height")
        val KEY_ACTIVITY_LEVEL = stringPreferencesKey(name = "activityLevel")
        val KEY_GOAL_TYPE = stringPreferencesKey(name = "goalType")
        val KEY_CARB_RATIO = floatPreferencesKey(name = "carbRatio")
        val KEY_PROTEIN_RATIO = floatPreferencesKey(name = "proteinRatio")
        val KEY_FAT_RATIO = floatPreferencesKey(name = "fatRatio")
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore(context).edit { preferences ->
            preferences[PreferenceKeys.KEY_ON_BOARDING] = completed
        }
    }

    suspend fun saveGender(gender: Gender) {
        dataStore(context).edit { preferences ->
            preferences[PreferenceKeys.KEY_GENDER] = gender.name
            Toast.makeText(context, "Gender Saved ${gender.name}", Toast.LENGTH_SHORT).show()

        }
    }

    suspend fun saveActivityLevel(activityLevel: ActivityLevel) {
        dataStore(context).edit { preferences ->
            preferences[PreferenceKeys.KEY_ACTIVITY_LEVEL] = activityLevel.name
            Toast.makeText(context, "Activity Level Saved: ${activityLevel.name}", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun saveGoalType(goalType: GoalType) {
        dataStore(context).edit { preferences ->
            preferences[PreferenceKeys.KEY_GOAL_TYPE] = goalType.name
            Toast.makeText(context, "Goal Saved: ${goalType.name}", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun saveAge(age: Int) {
        dataStore(context).edit { preferences ->
            preferences[PreferenceKeys.KEY_AGE] = age
            Toast.makeText(context, "Age Saved $age", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun saveHeight(height: Int) {
        dataStore(context).edit { preferences ->
            preferences[PreferenceKeys.KEY_HEIGHT] = height
            Toast.makeText(context, "Height Saved $height", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun saveWeight(weight: Int) {
        dataStore(context).edit { preferences ->
            preferences[PreferenceKeys.KEY_WEIGHT] = weight
            Toast.makeText(context, "Weight Saved $weight", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun saveProteinRatio(proteinRatio: Float) {
        dataStore(context).edit { preferences ->
            preferences[PreferenceKeys.KEY_PROTEIN_RATIO] = proteinRatio
            Toast.makeText(context, "Nutrients Saved", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun saveFatRatio(fatRatio: Float) {
        dataStore(context).edit { preferences ->
            preferences[PreferenceKeys.KEY_FAT_RATIO] = fatRatio
        }
    }

    suspend fun saveCarbRatio(carbRatio: Float) {
        dataStore(context).edit { preferences ->
            preferences[PreferenceKeys.KEY_CARB_RATIO] = carbRatio
        }
    }

    val readUserInfo: Flow<UserInfo> = dataStore(context).data.map { preferences ->
        val age = preferences[PreferenceKeys.KEY_AGE] ?: -1
        val genderString = preferences[PreferenceKeys.KEY_GENDER]
        val height = preferences[PreferenceKeys.KEY_HEIGHT] ?: -1
        val weight = preferences[PreferenceKeys.KEY_WEIGHT] ?: -1
        val goalTypeString = preferences[PreferenceKeys.KEY_GOAL_TYPE]
        val carbRatio = preferences[PreferenceKeys.KEY_CARB_RATIO] ?: -1
        val proteinRatio = preferences[PreferenceKeys.KEY_PROTEIN_RATIO] ?: -1
        val fatRatio = preferences[PreferenceKeys.KEY_FAT_RATIO] ?: -1
        val activityLevelString = preferences[PreferenceKeys.KEY_ACTIVITY_LEVEL]
        UserInfo(
            age = age,
            gender = Gender.fromString(genderString ?: "male"),
            height = height,
            weight = weight,
            goalType = GoalType.fromString(goalTypeString ?: "keep_weight"),
            activityLevel = ActivityLevel.fromString(activityLevelString ?: "medium"),
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio
        )
    }


    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore(context).data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferenceKeys.KEY_ON_BOARDING] ?: false
                onBoardingState
            }
    }


}