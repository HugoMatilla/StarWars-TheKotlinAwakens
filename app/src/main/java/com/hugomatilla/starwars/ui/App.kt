package com.hugomatilla.starwars.ui

import android.app.Application
import com.facebook.stetho.Stetho
import com.hugomatilla.starwars.data.db.DbHelper

/**
 * Created by hugomatilla on 20/03/16.
 */

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        DbHelper.init(instance)
        Stetho.initializeWithDefaults(this)
    }
}