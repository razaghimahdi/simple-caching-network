package com.example.simplecachingnetwork.features.resturant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplecachingnetwork.R
import com.example.simplecachingnetwork.databinding.ActivityRestaurantBinding
import com.example.simplecachingnetwork.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantActivity : AppCompatActivity() {

    val TAG="RestaurantActivity"

    private val viewModel:RestaurantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_restaurant)

        val restaurantAdapter = RestaurantAdapter()

        binding.apply {
            recyclerView.apply {
                adapter=restaurantAdapter
                layoutManager=LinearLayoutManager(this@RestaurantActivity)
            }

            /*
            viewModel.restaurants.observe(this@RestaurantActivity){resaurants->
                restaurantAdapter.submitList(resaurants)
                Log.i(TAG, "onCreate resaurants: "+resaurants.toList())
            }*/


            viewModel.restaurants.observe(this@RestaurantActivity) { result ->
                restaurantAdapter.submitList(result.data)
                Log.i(TAG, "onCreate result: $result")
                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                textViewError.text = result.error?.localizedMessage
            }


        }


    }
}