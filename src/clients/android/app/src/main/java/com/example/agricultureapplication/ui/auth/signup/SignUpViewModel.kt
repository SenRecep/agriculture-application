package com.example.agricultureapplication.ui.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agricultureapplication.models.api.ApiError
import com.example.agricultureapplication.models.user.UserSignUp
import com.example.agricultureapplication.services.apiServices.AuthService
import com.example.agricultureapplication.utility.IViewModelState
import com.example.agricultureapplication.utility.LoadingState
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel(), IViewModelState {
    override var loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    override var errorState: MutableLiveData<ApiError?> = MutableLiveData()

    fun signUp(userSignUp: UserSignUp): LiveData<Boolean> {
        var status = MutableLiveData<Boolean>()
        loadingState.value = LoadingState.Loading

        viewModelScope.launch {
            var res = AuthService.signUp(userSignUp)
            status.value=res.isSuccessful
            loadingState.value=LoadingState.Loaded
            if (!res.isSuccessful) errorState.value=res.error
        }
        return status
    }
}