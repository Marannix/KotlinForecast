package com.marannix.android.kotlinforecast.data.provider

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.marannix.android.kotlinforecast.internal.UnitSystem

const val UNIT_SYSTEM = "UNIT_SYSTEM"
class UnitProviderImpl(context: Context) : UnitProvider {

    private val appContext = context.applicationContext

    private val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    override fun getUnitSystem(): UnitSystem {
        val selectedName = preferences.getString(UNIT_SYSTEM, UnitSystem.METRIC.name)
        return UnitSystem.valueOf(selectedName!!)

    }
}