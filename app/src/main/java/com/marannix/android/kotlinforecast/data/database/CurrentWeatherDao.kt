package com.marannix.android.kotlinforecast.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marannix.android.kotlinforecast.data.database.entity.CURRENT_WEATHER_ID
import com.marannix.android.kotlinforecast.data.database.entity.CurrentWeatherEntry
import com.marannix.android.kotlinforecast.data.database.unitlocalised.ImperialCurrentWeatherEntry
import com.marannix.android.kotlinforecast.data.database.unitlocalised.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry>

    @Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEntry>
}