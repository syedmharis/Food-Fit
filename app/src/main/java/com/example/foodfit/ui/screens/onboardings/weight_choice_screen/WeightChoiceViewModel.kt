package com.example.foodfit.ui.screens.onboardings.weight_choice_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodfit.data.datastore.CalorieTrackerDataStore
import com.example.foodfit.domain.on_boarding.model.GoalType
import kotlinx.coroutines.launch


class WeightChoiceViewModel(
    private val dataStore: CalorieTrackerDataStore
):ViewModel(){

    private var selectedGoalType = mutableStateOf<GoalType>(GoalType.LoseWeight)

    fun onGoalLevelSelected(goalType: GoalType){
        selectedGoalType.value = goalType
    }

    fun onNextClick(){
        viewModelScope.launch {
            dataStore.saveGoalType(selectedGoalType.value)
        }
    }

}