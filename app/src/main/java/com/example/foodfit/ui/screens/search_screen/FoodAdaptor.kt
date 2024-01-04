package com.example.foodfit.ui.screens.search_screen// com.example.foodfit.ui.screens.search_screen.FoodAdapter.kt
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodfit.data.remote.dto.Product
import com.example.foodfit.data.remote.dto.SearchDto
import com.example.foodfit.databinding.ListItemFoodBinding
import com.squareup.picasso.Picasso

class FoodAdapter(
    private val context: Context,
    private val foodList: MutableList<Product>,
    private val foodAdapterListener: FoodAdapterListener
) :
    RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foodItem = foodList[position]
        holder.bind(foodItem)
    }

    override fun getItemCount(): Int = foodList.size

    fun updateData(searchDto: SearchDto) {
        val newFoodList = searchDto.products
        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()
    }

    interface FoodAdapterListener {
        fun addItem(product: Product)
    }


    inner class ViewHolder(itemView: View, private val binding: ListItemFoodBinding) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(foodItem: Product) {
            // Bind data to the ViewHolder components
            binding.foodName.text = foodItem.productName?.take(30)
            Picasso.get().load(foodItem.imageFrontThumbUrl).into(binding.foodImage)
            val formattedCalories = String.format("%.2f", foodItem.nutriments.energyKcal100g)
            binding.foodCal.text = "${formattedCalories}kcal"
            binding.foodCarb.text = "${foodItem.nutriments.carbohydrates100g}g"
            binding.foodProtein.text = "${foodItem.nutriments.proteins100g}g"
            binding.foodFat.text = "${foodItem.nutriments.fat100g}g"

            val headerView = binding.headerView
            val viewDetail = binding.viewDetail
            val arrowIcon = binding.arrowIcon
            binding.addFood.setOnClickListener {
                foodAdapterListener.addItem(product = foodItem)
            }

            var isExpanded = false // Initially, the content is collapsed

            headerView.setOnClickListener {
                // Toggle the visibility of viewDetail with animation
                if (isExpanded) {
                    // Collapse animation
                    viewDetail.animate()
                        .alpha(0f) // Fade out
                        .translationY(-viewDetail.height.toFloat()) // Slide up
                        .withEndAction {
                            // After animation ends, hide the view
                            viewDetail.visibility = View.GONE
                        }
                } else {
                    // Expand animation
                    viewDetail.visibility = View.VISIBLE // Make it visible before animation
                    viewDetail.animate()
                        .alpha(1f) // Fade in
                        .translationY(0f) // Slide down

                }

                // Rotate the arrow icon to indicate the state
                val rotationDegrees = if (isExpanded) 0f else 180f // 180 degrees for rotation
                arrowIcon.animate().rotation(rotationDegrees)

                isExpanded = !isExpanded // Toggle the state
            }
        }
    }
}
