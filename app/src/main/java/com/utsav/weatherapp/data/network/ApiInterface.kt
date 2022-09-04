package com.utsav.weatherapp.data.network

import com.utsav.weatherapp.BuildConfig
import com.utsav.weatherapp.data.model.WeatherDataResponse
import com.utsav.weatherapp.data.model.WeeklyWeatherResponse
import com.utsav.weatherapp.util.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiInterface {

    // <editor-fold desc="Get Requests">

    @GET("weather")
    suspend fun findCityWeatherData(
        @Query("q") q: String,
        @Query("units") units: String = AppConstants.WEATHER_UNIT,
        @Query("appid") appid: String = BuildConfig.weather_api_key
    ): Response<WeatherDataResponse>

    // </editor-fold>

    // <editor-fold desc="Get Requests">

    @GET("forecast")
    suspend fun getCityWeeklyWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lng: Double,
        @Query("units") units: String = AppConstants.WEATHER_UNIT,
        @Query("appid") appid: String = BuildConfig.weather_api_key
    ): Response<WeeklyWeatherResponse>

    // </editor-fold>






    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): ApiInterface {

            val WS_SERVER_URL = BuildConfig.end_point
            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(WS_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
        }
    }
}
