package com.utsav.weatherapp

import android.app.Application
import com.utsav.weatherapp.data.local.WeatherDatabase
import com.utsav.weatherapp.data.network.ApiInterface
import com.utsav.weatherapp.data.network.NetworkConnectionInterceptor
import com.utsav.weatherapp.data.repositories.WeatherRepository
import com.utsav.weatherapp.ui.viewmodelfactory.WeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class WeatherApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@WeatherApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiInterface(instance()) }
        bind() from singleton { WeatherRepository(instance(), instance()) }
        bind() from provider { WeatherViewModelFactory(instance()) }
        bind() from provider { WeatherDatabase(instance()) }
    }


}
