package com.utsav.weatherapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.utsav.weatherapp.R
import com.utsav.weatherapp.data.model.WeeklyWeatherResponse

import com.utsav.weatherapp.databinding.ItemWeeklyWeatherBinding
import com.utsav.weatherapp.util.AppConstants
import com.utsav.weatherapp.util.AppUtils

class WeeklyWeatherAdapter :
    RecyclerView.Adapter<WeeklyWeatherAdapter.ViewHolder>() {


    private val weatherDetailList = ArrayList<WeeklyWeatherResponse.WeatherDetails>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemWeeklyWeatherBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_weekly_weather,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(weatherDetailList[position])
    }

    override fun getItemCount(): Int = weatherDetailList.size


    fun setData(
        newWeeklyData: List<WeeklyWeatherResponse.WeatherDetails>
    ) {
        weatherDetailList.clear()
        weatherDetailList.addAll(newWeeklyData)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemWeeklyWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindItems(weatherDetail: WeeklyWeatherResponse.WeatherDetails) {
            binding.apply {
                val iconCode = weatherDetail.weather.first().icon
                AppUtils.setGlideImage(
                    itemWeeklyWeatherIcon,
                    AppConstants.WEATHER_API_IMAGE_ENDPOINT + "${iconCode}@4x.png"
                )
                itemWeeklyWeatherDay.text = "${weatherDetail.dt?.let { AppUtils.getDayOfWeek(it) }}"
                itemWeeklyWeatherMin.text = "Min\n${weatherDetail.main?.tempMin}\u2103"
                itemWeeklyWeatherMax.text = "Max\n${weatherDetail.main?.tempMax}\u2103"
                itemWeeklyWeatherFeelsLike.text = "Feels like\n ${weatherDetail.main?.feelsLike}\u2103"
            }
        }
    }
}