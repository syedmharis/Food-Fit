package com.example.foodfit.ui.screens.onboardings.age

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodfit.data.datastore.CalorieTrackerDataStore
import com.example.foodfit.domain.on_boarding.use_cases.FilterOutDigits
import kotlinx.coroutines.launch



class AgeViewModel(
    private val dataStore: CalorieTrackerDataStore
):ViewModel() {

    private val filterOutDigits = FilterOutDigits()

    private var age:MutableState<String> = mutableStateOf("20")

    fun onAgeChange(age:String){
            this.age.value= filterOutDigits(age)
    }

    fun saveToPreference(){
        viewModelScope.launch {
            val ageNumber = age.value.toInt()
            Log.d("age", "Age Saved :success")
            dataStore.saveAge(ageNumber)
        }
    }
}
