package com.marannix.android.kotlinforecast.data.provider

import com.marannix.android.kotlinforecast.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem() : UnitSystem
}