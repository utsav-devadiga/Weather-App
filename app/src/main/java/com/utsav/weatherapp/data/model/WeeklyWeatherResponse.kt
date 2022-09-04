package com.utsav.weatherapp.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WeeklyWeatherResponse (

    @SerializedName("cod"     ) var cod     : String?         = null,
    @SerializedName("message" ) var message : Int?            = null,
    @SerializedName("cnt"     ) var cnt     : Int?            = null,
    @SerializedName("list"    ) var list    : ArrayList<WeatherDetails> = arrayListOf(),
    @SerializedName("city"    ) var city    : City?           = City()

){
    @Keep
    data class Main (

        @SerializedName("temp"       ) var temp      : Double? = null,
        @SerializedName("feels_like" ) var feelsLike : Double? = null,
        @SerializedName("temp_min"   ) var tempMin   : Double? = null,
        @SerializedName("temp_max"   ) var tempMax   : Double? = null,
        @SerializedName("pressure"   ) var pressure  : Int?    = null,
        @SerializedName("sea_level"  ) var seaLevel  : Int?    = null,
        @SerializedName("grnd_level" ) var grndLevel : Int?    = null,
        @SerializedName("humidity"   ) var humidity  : Int?    = null,
        @SerializedName("temp_kf"    ) var tempKf    : Double? = null

    )

    @Keep
    data class Weather (

        @SerializedName("id"          ) var id          : Int?    = null,
        @SerializedName("main"        ) var main        : String? = null,
        @SerializedName("description" ) var description : String? = null,
        @SerializedName("icon"        ) var icon        : String? = null

    )

    @Keep
    data class Clouds (

        @SerializedName("all" ) var all : Int? = null

    )

    @Keep
    data class Wind (

        @SerializedName("speed" ) var speed : Double? = null,
        @SerializedName("deg"   ) var deg   : Int?    = null,
        @SerializedName("gust"  ) var gust  : Double? = null

    )

    @Keep
    data class Sys (

        @SerializedName("pod" ) var pod : String? = null

    )

    data class WeatherDetails (

        @SerializedName("dt"         ) var dt         : Long?               = null,
        @SerializedName("main"       ) var main       : Main?              = Main(),
        @SerializedName("weather"    ) var weather    : ArrayList<Weather> = arrayListOf(),
        @SerializedName("clouds"     ) var clouds     : Clouds?            = Clouds(),
        @SerializedName("wind"       ) var wind       : Wind?              = Wind(),
        @SerializedName("visibility" ) var visibility : Int?               = null,
        @SerializedName("pop"        ) var pop        : Double?            = null,
        @SerializedName("sys"        ) var sys        : Sys?               = Sys(),
        @SerializedName("dt_txt"     ) var dtTxt      : String?            = null

    )


    data class Coord (

        @SerializedName("lat" ) var lat : Double? = null,
        @SerializedName("lon" ) var lon : Double? = null

    )

    data class City (

        @SerializedName("id"         ) var id         : Int?    = null,
        @SerializedName("name"       ) var name       : String? = null,
        @SerializedName("coord"      ) var coord      : Coord?  = Coord(),
        @SerializedName("country"    ) var country    : String? = null,
        @SerializedName("population" ) var population : Int?    = null,
        @SerializedName("timezone"   ) var timezone   : Int?    = null,
        @SerializedName("sunrise"    ) var sunrise    : Int?    = null,
        @SerializedName("sunset"     ) var sunset     : Int?    = null

    )

}