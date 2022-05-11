package com.example.agricultureapplication.ui.posts.postsList

import android.location.Location
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agricultureapplication.models.api.ApiError
import com.example.agricultureapplication.models.api.Pager
import com.example.agricultureapplication.models.webapi.Post
import com.example.agricultureapplication.services.LocationData
import com.example.agricultureapplication.services.LocationService
import com.example.agricultureapplication.services.apiServices.PostsService
import com.example.agricultureapplication.utility.GlobalApp
import com.example.agricultureapplication.utility.IViewModelState
import com.example.agricultureapplication.utility.LoadingState
import kotlinx.coroutines.launch

class PostsListViewModel : ViewModel(), IViewModelState {
    override var loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    override var errorState: MutableLiveData<ApiError?> = MutableLiveData()
    var posts: MutableLiveData<ArrayList<Post>> = MutableLiveData()
    var locationData: MutableLiveData<LocationData> = MutableLiveData()
    lateinit var locationService: LocationService

    fun findLocation() {
        loadingState.value = LoadingState.Loading
        locationService.getCurrentLocation().addOnCompleteListener() {
            val location: Location? = it.result
            if (location == null) {
                Toast.makeText(GlobalApp.getAppContext(), "Null Received", Toast.LENGTH_SHORT)
                    .show()
            } else {
                locationData.value = LocationData(
                    location.latitude.toFloat(), location.longitude.toFloat(), locationService.getCompleteAddressString(
                        location.latitude,
                        location.longitude,
                        GlobalApp.getAppContext()
                    )
                )
                loadingState.value = LoadingState.Loaded
            }
        }
    }

    fun requestPermissionsResult(requestCode: Int, grantResults: IntArray) {
        viewModelScope.launch {
            locationService.requestPermissionsResult(requestCode, grantResults)
        }
    }

    fun getPosts(pager: Pager) {
        loadingState.value = LoadingState.Loading
        viewModelScope.launch {
            var response= PostsService.getPosts(pager)
            if (!response.isSuccessful)
                errorState.value= response.error
            else
                posts.value= response.data!!
            loadingState.value= LoadingState.Loaded
        }
    }
}