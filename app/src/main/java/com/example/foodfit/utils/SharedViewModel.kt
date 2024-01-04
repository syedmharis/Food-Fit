
import androidx.lifecycle.ViewModel
import com.example.foodfit.data.datastore.CalorieTrackerDataStore
import com.example.foodfit.ui.screens.onboardings.age.AgeViewModel
import com.example.foodfit.ui.screens.onboardings.gender.GenderViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val calorieTrackerDataStore: CalorieTrackerDataStore
) : ViewModel() {
    lateinit var ageViewModel: AgeViewModel
    lateinit var genderViewModel: GenderViewModel

    init {
        ageViewModel = AgeViewModel(calorieTrackerDataStore)
        genderViewModel = GenderViewModel(calorieTrackerDataStore)
    }
}
