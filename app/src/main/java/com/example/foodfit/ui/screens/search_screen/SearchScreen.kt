package com.example.foodfit.ui.screens.search_screen

import DataModule.provideTrackerDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.foodfit.data.local.FoodDatabase
import com.example.foodfit.data.mapper.toTrackedFood
import com.example.foodfit.data.remote.CalorieApi
import com.example.foodfit.data.remote.dto.Product
import com.example.foodfit.data.remote.dto.SearchDto
import com.example.foodfit.databinding.FragmentSearchableBinding
import com.example.foodfit.di.DataModule
import com.example.foodfit.domain.food_tracker.respository.FoodTrackerRepositoryImpl
import com.example.foodfit.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchScreen : Fragment() {

    private var _binding: FragmentSearchableBinding? = null
    private val binding get() = _binding!!
    private lateinit var apiService: CalorieApi
    private lateinit var app: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchableBinding.inflate(inflater, container, false)
        val view = binding.root

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://us.openfoodfacts.org/") // replace with your actual base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val app = app
        val foodDatabase = provideTrackerDatabase(app)
        val db = DataModule.provideTrackerDatabase(con)
        provideTrackerFoodRepository
        // Create ApiService instance
        apiService = retrofit.create(CalorieApi::class.java)// Initialize the FoodDatabase
        val foodDatabase = Room.databaseBuilder(
            requireContext(),
            FoodDatabase::class.java,
            "food_database" // Replace with your desired database name
        ).build()

        val tracker = FoodTrackerRepositoryImpl(foodDatabase)

        // Initialize RecyclerView and adapter
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val foodAdapter = FoodAdapter(requireContext(), mutableListOf(), object : FoodAdapter.FoodAdapterListener{
            override fun addItem(product: Product) {
                GlobalScope.launch {
                    tracker.insertTrackedFood(product.toTrackedFood())
                }
            }
        })

        recyclerView.adapter = foodAdapter

        // Set up SearchView listener
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    showLoading(true) // Show loading indicator

                    // Make Retrofit API call with the query
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val result = apiService.getFood(query, 1, 20)
                            withContext(Dispatchers.Main) {
                                showLoading(false) // Hide loading indicator
                                val productsWithImages =
                                    result.products.filter { !it.imageFrontThumbUrl.isNullOrEmpty() }
                                if (productsWithImages.isNotEmpty()) {
                                    foodAdapter.updateData(SearchDto(productsWithImages))
                                } else {
                                    showNoResultsMessage()
                                }
                            }
                        } catch (e: Exception) {
                            withContext(Dispatchers.Main) {
                                showLoading(false) // Hide loading indicator
                                showErrorMessage()
                            }
                        }
                    }
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle text change if needed
                return true
            }
        })

        return view
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showNoResultsMessage() {
        Toast.makeText(requireContext(), "No results found", Toast.LENGTH_SHORT).show()
    }

    private fun showErrorMessage() {
        Toast.makeText(requireContext(), "An error occurred", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
