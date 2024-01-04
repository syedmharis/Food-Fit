package com.example.foodfit.ui.screens.onboardings.nutrient_goal

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodfit.data.datastore.CalorieTrackerDataStore
import kotlinx.coroutines.launch



class NutrientGoalViewModel(
    private val dataStore: CalorieTrackerDataStore
):ViewModel() {

    private var carbs:MutableState<Int> = mutableStateOf(40)
    private var proteins:MutableState<Int> = mutableStateOf(30)
    private var fats:MutableState<Int> = mutableStateOf(30)

    fun onCarbChange(carb:Int){
        this.carbs.value= carb
    }
    fun onProteinChange(protein:Int){
        this.proteins.value= protein
    }
    fun onFatChange(fat:Int) {
        this.fats.value = fat
    }


    fun saveToPreference(){
        viewModelScope.launch {
            dataStore.saveCarbRatio(carbs.value.toFloat())
            dataStore.saveProteinRatio(proteins.value.toFloat())
            dataStore.saveFatRatio(fats.value.toFloat())
        }
    }
}
