package com.example.simplecachingnetwork.features.resturant

import androidx.lifecycle.*
import com.example.simplecachingnetwork.api.RestaurantApi
import com.example.simplecachingnetwork.data.Restaurant
import com.example.simplecachingnetwork.data.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    repository: RestaurantRepository
//api:RestaurantApi
) : ViewModel() {

    val restaurants = repository.getRestaurants().asLiveData()

   /* private val restaurantLiveData=MutableLiveData<List<Restaurant>>()
    val restaurants:LiveData<List<Restaurant>> = restaurantLiveData

    init {
        viewModelScope.launch {
            val restaurants = api.getRestaurants()
            delay(2000)
                restaurantLiveData.value=restaurants

        }
    }

*/
}