package com.marannix.android.kotlinforecast.data.network

import androidx.lifecycle.LiveData
import com.marannix.android.kotlinforecast.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        languageCode: String
    )
}