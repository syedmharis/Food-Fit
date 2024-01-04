package com.example.foodfit.ui.screens.onboardings.activity_level

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodfit.data.datastore.CalorieTrackerDataStore
import com.example.foodfit.domain.on_boarding.model.ActivityLevel
import kotlinx.coroutines.launch


class ActivityLevelViewModel(
    private val dataStore: CalorieTrackerDataStore
):ViewModel() {

    private var selectedActivityLevel = mutableStateOf<ActivityLevel>(ActivityLevel.Medium)

    fun onActivityLevelSelected(activityLevel: ActivityLevel){
        selectedActivityLevel.value = activityLevel
    }

    fun onNextClick(){
        viewModelScope.launch {
            dataStore.saveActivityLevel(selectedActivityLevel.value)
        }
    }

}