package com.hugomatilla.starwars.base

import android.content.Context
import android.support.v4.content.ContextCompat
import com.hugomatilla.starwars.app.App

/**
 * Created by hugomatilla on 24/05/16.
 */

fun Int.toColor(context: Context): Int {
    return ContextCompat.getColor(context, this);
}

fun Int.toResourceString(): String {
    return App.instance.getString(this);
}