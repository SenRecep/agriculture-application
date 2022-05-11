package com.example.agricultureapplication.utility

import androidx.lifecycle.MutableLiveData
import com.example.agricultureapplication.models.api.ApiError

interface IViewModelState {
    var loadingState: MutableLiveData<LoadingState>
    var errorState:MutableLiveData<ApiError?>
}