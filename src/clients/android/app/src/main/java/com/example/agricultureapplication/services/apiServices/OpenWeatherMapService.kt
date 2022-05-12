package com.example.agricultureapplication.services.apiServices

import com.example.agricultureapplication.consts.ApiConsts
import com.example.agricultureapplication.models.api.ApiResponse
import com.example.agricultureapplication.models.openweathermap.OpenWeatherMap
import com.example.agricultureapplication.services.retrofitServices.ApiClient
import com.example.agricultureapplication.services.retrofitServices.RetrofitOpenWeatherMapService
import com.example.agricultureapplication.utility.HelperService

class OpenWeatherMapService {
    companion object {
        private var retrofitService =
            ApiClient.buildService(ApiConsts.webApiBaseUrl, RetrofitOpenWeatherMapService::class.java, false);

        suspend fun getWeather(city:String): ApiResponse<OpenWeatherMap> {
            try {
                var response = retrofitService.getWeather(city);
                if (!response.isSuccessful) return HelperService.handleApiError(response);
                var data= response.body()!!;
                return ApiResponse(true,data);
            } catch (ex: Exception) {
                return HelperService.handleException(ex);
            }
        }


    }
}