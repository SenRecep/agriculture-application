package com.example.agricultureapplication.ui.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agricultureapplication.models.api.ApiError
import com.example.agricultureapplication.models.user.UserSignIn
import com.example.agricultureapplication.services.apiServices.AuthService
import com.example.agricultureapplication.utility.IViewModelState
import com.example.agricultureapplication.utility.LoadingState
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel(), IViewModelState {
    override var loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    override var errorState: MutableLiveData<ApiError?> = MutableLiveData()

    fun signIn(signIn: UserSignIn): LiveData<Boolean> {
        loadingState.value = LoadingState.Loading
        var status = MutableLiveData<Boolean>()
        viewModelScope.launch {
            var apiResponse = AuthService.signIn(signIn)
            status.value=apiResponse.isSuccessful
            if (!apiResponse.isSuccessful)
                errorState.value=apiResponse.error
            loadingState.value=LoadingState.Loaded
        }
        return status
    }


}