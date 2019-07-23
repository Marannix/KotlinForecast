package com.marannix.android.kotlinforecast.data.network.response

import com.google.gson.annotations.SerializedName
import com.marannix.android.kotlinforecast.data.database.entity.CurrentWeatherEntry
import com.marannix.android.kotlinforecast.data.database.entity.Location


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location
)