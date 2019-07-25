package com.marannix.android.kotlinforecast.ui.weather.current

import androidx.lifecycle.ViewModel;
import com.marannix.android.kotlinforecast.data.provider.UnitProvider
import com.marannix.android.kotlinforecast.data.repository.ForecastRepository
import com.marannix.android.kotlinforecast.internal.UnitSystem
import com.marannix.android.kotlinforecast.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    private val unitProvider: UnitProvider
) : ViewModel() {
    // get from settings later
    private val unitSystem = unitProvider.getUnitSystem()

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(metric = isMetric)
    }
}
