package com.example.agricultureapplication.ui.posts.postsDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agricultureapplication.models.api.ApiError
import com.example.agricultureapplication.models.webapi.Post
import com.example.agricultureapplication.services.apiServices.PostsService
import com.example.agricultureapplication.utility.IViewModelState
import com.example.agricultureapplication.utility.LoadingState
import kotlinx.coroutines.launch

class PostsDetailViewModel : ViewModel(), IViewModelState {
    override var loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    override var errorState: MutableLiveData<ApiError?> = MutableLiveData()
    var post: MutableLiveData<Post> = MutableLiveData()
    var isOwner: MutableLiveData<Boolean> = MutableLiveData()

    fun checkOwner(postId: Int) {
        viewModelScope.launch {
            var response = PostsService.getCheckOwner(postId)
            if (response.isSuccessful)
                isOwner.value = response.data!!
        }
    }

    fun deletePost(postId: Int):LiveData<Boolean>  {
        loadingState.value = LoadingState.Loading
        var status=MutableLiveData<Boolean>()
        viewModelScope.launch {
            var response = PostsService.deletePost(postId)
            if (response.isSuccessful)
                status.value=true
            else
                errorState.value = response.error
            loadingState.value = LoadingState.Loaded
        }
        return  status
    }

}