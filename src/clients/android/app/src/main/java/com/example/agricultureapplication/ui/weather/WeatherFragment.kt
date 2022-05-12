package com.example.agricultureapplication.ui.weather

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.agricultureapplication.R
import kotlinx.android.synthetic.main.weather_fragment.view.*

class WeatherFragment : Fragment() {
    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        var fragmentView = inflater.inflate(R.layout.weather_fragment, container, false)

        viewModel.weather.observe(viewLifecycleOwner) {
            Log.d("weather", it.toString())
            fragmentView.txt_weather_result.text = it.toString()
        }

        fragmentView.btn_weather_show.setOnClickListener {
            var city = fragmentView.txt_weather_city.text.toString()
            viewModel.getWeather(city)
        }

        return fragmentView
    }
}