package com.example.foodfit.ui.screens.onboardings.gender

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodfit.data.datastore.CalorieTrackerDataStore
import com.example.foodfit.domain.on_boarding.model.Gender
import kotlinx.coroutines.launch

class GenderViewModel(
    private val dataStore: CalorieTrackerDataStore
):ViewModel() {

    private var selectedGender: Gender = Gender.Male

    fun onGenderClick(gender: Gender) {
        selectedGender = gender
    }

    fun saveToPreference() {
        viewModelScope.launch {
            dataStore.saveGender(selectedGender)

        }
    }


}
