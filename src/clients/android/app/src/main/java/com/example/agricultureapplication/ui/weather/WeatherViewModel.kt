package com.example.agricultureapplication.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agricultureapplication.models.api.ApiError
import com.example.agricultureapplication.models.openweathermap.OpenWeatherMap
import com.example.agricultureapplication.services.apiServices.OpenWeatherMapService
import com.example.agricultureapplication.utility.IViewModelState
import com.example.agricultureapplication.utility.LoadingState
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel(), IViewModelState {
    override var loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    override var errorState: MutableLiveData<ApiError?> = MutableLiveData()
    var weather: MutableLiveData<OpenWeatherMap> = MutableLiveData()

    fun getWeather(city:String){
        loadingState.value = LoadingState.Loading
        viewModelScope.launch {
            var response= OpenWeatherMapService.getWeather(city)
            if (!response.isSuccessful)
                errorState.value= response.error
            else
                weather.value= response.data!!
            loadingState.value= LoadingState.Loaded
        }
    }
}