package com.example.agricultureapplication.ui.posts.postsUpdate

import android.location.Location
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agricultureapplication.models.api.ApiError
import com.example.agricultureapplication.models.webapi.dto.PostUpdateDto
import com.example.agricultureapplication.services.LocationData
import com.example.agricultureapplication.services.LocationService
import com.example.agricultureapplication.services.apiServices.PostsService
import com.example.agricultureapplication.utility.GlobalApp
import com.example.agricultureapplication.utility.IViewModelState
import com.example.agricultureapplication.utility.LoadingState
import kotlinx.coroutines.launch

class PostsUpdateViewModel : ViewModel() , IViewModelState {
    override var loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    override var errorState: MutableLiveData<ApiError?> = MutableLiveData()

    fun updatePost(id:Int,postUpdateDto: PostUpdateDto): LiveData<Boolean> {
        loadingState.value = LoadingState.Loading
        val result = MutableLiveData<Boolean>()
        viewModelScope.launch {
            var response = PostsService.updatePost(id,postUpdateDto)
            result.value=response.isSuccessful
            if (!response.isSuccessful) {
                errorState.value=response.error
                loadingState.value= LoadingState.Loaded
            }
        }
        return result

    }
}