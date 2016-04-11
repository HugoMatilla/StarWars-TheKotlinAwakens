package com.hugomatilla.starwars.base

import android.app.Activity
import android.util.DisplayMetrics

/**
 * Created by hugomatilla on 11/04/16.
 */

// in Anko
val Activity.metrics: DisplayMetrics
    get() = getDeviceMetrics(this)

fun getDeviceMetrics(activity: Activity): DisplayMetrics {
    val metrics = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(metrics)
    return metrics
}

