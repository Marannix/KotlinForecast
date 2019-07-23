package com.marannix.android.kotlinforecast.data.repository

import androidx.lifecycle.LiveData
import com.marannix.android.kotlinforecast.data.database.CurrentWeatherDao
import com.marannix.android.kotlinforecast.data.database.unitlocalised.UnitSpecificCurrentWeatherEntry
import com.marannix.android.kotlinforecast.data.network.WeatherNetworkDataSource
import com.marannix.android.kotlinforecast.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever { newCurrentWeather ->
            // When the data is fetched from the Api, we persist it to the local database
            persistFetchedCurrentWeather(newCurrentWeather)
        }
    }

    // out means, it tells the generic that we can also return classes that only implement UnitSpecificCurrentWeatherEntry and not only directly UnitSpecificCurrentWeatherEntry
    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
        // withContext returns a value
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext if (metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        // Dispatchers.IO -> Allows to spin out many thread at once and then destroy them without any performance penalty
        // launch doesn't return a value instead it returns a job
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
        }
    }

    private suspend fun initWeatherData() {
        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather() {
        weatherNetworkDataSource.fetchCurrentWeather(
            "Los Angeles", Locale.getDefault().language
        )
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinuteAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinuteAgo)
    }
}