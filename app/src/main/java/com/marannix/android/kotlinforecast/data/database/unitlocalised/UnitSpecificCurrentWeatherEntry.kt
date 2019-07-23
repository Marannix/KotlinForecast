package com.marannix.android.kotlinforecast.data.database.unitlocalised

interface UnitSpecificCurrentWeatherEntry {
    val temperature: Double
    val conditionText: String
    val conditionIconUrl: String
    val windSpeed: Double
    val windDirection: String
    val precipitationVolume: String
    val feelsLikeTemperature: Double
    val visibilityDistance: Double
}