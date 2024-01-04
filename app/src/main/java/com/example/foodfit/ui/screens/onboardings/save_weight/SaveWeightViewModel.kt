package com.example.foodfit.ui.screens.onboardings.save_weight

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodfit.data.datastore.CalorieTrackerDataStore
import com.example.foodfit.domain.on_boarding.use_cases.FilterOutDigits
import kotlinx.coroutines.launch


class SaveWeightViewModel(
   private val dataStore: CalorieTrackerDataStore
):ViewModel() {

    private val filterOutDigits = FilterOutDigits()

    private var weight:MutableState<String> = mutableStateOf("80")

    fun onWeightChange(weight:String){
            this.weight.value= filterOutDigits(weight)
    }

    fun saveToPreference(){
        viewModelScope.launch {
            val weightNumber = weight.value.toInt()
            dataStore.saveWeight(weightNumber)
        }
    }

}