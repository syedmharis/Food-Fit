package com.example.foodfit.ui.screens.onboardings.height


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodfit.data.datastore.CalorieTrackerDataStore
import com.example.foodfit.domain.on_boarding.use_cases.FilterOutDigits
import kotlinx.coroutines.launch

class HeightViewModel (
    private val dataStore: CalorieTrackerDataStore
):ViewModel() {

    private var height: MutableState<String> = mutableStateOf("180")
    private val filterOutDigits = FilterOutDigits()
    fun onHeightChange(height:String){
            this.height.value = filterOutDigits(height)
    }

    fun saveToPreference(){
        viewModelScope.launch {
            dataStore.saveHeight(height.value.toInt())
        }
    }

}