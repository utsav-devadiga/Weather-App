package com.utsav.weatherapp.data.repositories

import com.utsav.weatherapp.data.local.WeatherDatabase
import com.utsav.weatherapp.data.model.WeatherDataResponse
import com.utsav.weatherapp.data.model.WeatherDetail
import com.utsav.weatherapp.data.model.WeeklyWeatherResponse
import com.utsav.weatherapp.data.network.ApiInterface
import com.utsav.weatherapp.data.network.SafeApiRequest

class WeatherRepository(
    private val api: ApiInterface,
    private val db: WeatherDatabase
) : SafeApiRequest() {

    suspend fun findCityWeather(cityName: String): WeatherDataResponse = apiRequest {
        api.findCityWeatherData(cityName)
    }

    suspend fun getCityWeeklyWeather(lat: Double,lng:Double): WeeklyWeatherResponse = apiRequest {
        api.getCityWeeklyWeatherData(lat, lng)
    }

    suspend fun addWeather(weatherDetail: WeatherDetail) {
        db.getWeatherDao().addWeather(weatherDetail)
    }

    suspend fun fetchWeatherDetail(cityName: String): WeatherDetail? =
        db.getWeatherDao().fetchWeatherByCity(cityName)

    suspend fun fetchAllWeatherDetails(): List<WeatherDetail> =
        db.getWeatherDao().fetchAllWeatherDetails()
}
