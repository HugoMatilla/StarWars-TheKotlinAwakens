package com.hugomatilla.starwars.ui.activities

import android.os.Handler
import android.os.Looper
import com.hugomatilla.starwars.domain.PostExecutionThread

/**
 * Created by hugomatilla on 28/02/16.
 */

object UiThread : PostExecutionThread {

    private val handler: Handler

    init {
        handler = Handler(Looper.getMainLooper())
    }

    override fun post(runnable: Runnable) {
        handler.post(runnable)
    }

}