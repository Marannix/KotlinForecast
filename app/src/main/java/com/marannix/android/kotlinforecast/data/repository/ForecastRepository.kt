package com.marannix.android.kotlinforecast.data.repository

import androidx.lifecycle.LiveData
import com.marannix.android.kotlinforecast.data.database.unitlocalised.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
}