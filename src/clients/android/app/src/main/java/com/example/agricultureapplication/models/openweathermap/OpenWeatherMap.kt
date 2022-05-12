package com.example.agricultureapplication.models.openweathermap

import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat

var df = DecimalFormat("#.##")

data class OpenWeatherMap (
    @SerializedName("coord") val coord : Coord,
    @SerializedName("weather") val weather : List<Weather>,
    @SerializedName("base") val base : String,
    @SerializedName("main") val main : Main,
    @SerializedName("visibility") val visibility : Int,
    @SerializedName("wind") val wind : Wind,
    @SerializedName("clouds") val clouds : Clouds,
    @SerializedName("dt") val dt : Int,
    @SerializedName("sys") val sys : Sys,
    @SerializedName("timezone") val timezone : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("cod") val cod : Int
){
    override fun toString(): String {
        var builder=StringBuilder()
        builder.append("Current weather of $name (${sys.country})")
        builder.append("\nTemp: ${df.format(main.temp-273.15)} °C")
        builder.append("\nFeels Like: ${df.format(main.feels_like-273.15)} °C")
        builder.append("\nHumidity: ${main.humidity}%")
        builder.append("\nDescription: ${weather[0].description}")
        builder.append("\nWind Speed: ${wind.speed}m/s")
        builder.append("\nCloudiness: ${clouds.all}%")
        builder.append("\nPressure: ${main.pressure} hPa")
        return builder.toString()
    }
}

data class Clouds (

    @SerializedName("all") val all : Int
)

data class Coord (

    @SerializedName("lon") val lon : Double,
    @SerializedName("lat") val lat : Double
)

data class Main (

    @SerializedName("temp") val temp : Double,
    @SerializedName("feels_like") val feels_like : Double,
    @SerializedName("temp_min") val temp_min : Double,
    @SerializedName("temp_max") val temp_max : Double,
    @SerializedName("pressure") val pressure : Int,
    @SerializedName("humidity") val humidity : Int,
    @SerializedName("sea_level") val sea_level : Int,
    @SerializedName("grnd_level") val grnd_level : Int
)

data class Sys (

    @SerializedName("country") val country : String,
    @SerializedName("sunrise") val sunrise : Int,
    @SerializedName("sunset") val sunset : Int
)

data class Weather (

    @SerializedName("id") val id : Int,
    @SerializedName("main") val main : String,
    @SerializedName("description") val description : String,
    @SerializedName("icon") val icon : String
)

data class Wind (

    @SerializedName("speed") val speed : Double,
    @SerializedName("deg") val deg : Int,
    @SerializedName("gust") val gust : Double
)