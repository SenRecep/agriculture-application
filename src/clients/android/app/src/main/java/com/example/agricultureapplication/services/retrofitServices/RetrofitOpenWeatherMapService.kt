package com.example.agricultureapplication.services.retrofitServices

import com.example.agricultureapplication.models.openweathermap.OpenWeatherMap
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitOpenWeatherMapService {
    @GET("https://api.openweathermap.org/data/2.5/weather?appid=3a63ab1c0b75bd51b2f70a4f15e59616")
    suspend fun getWeather(@Query("q") City:String): Response<OpenWeatherMap>
}