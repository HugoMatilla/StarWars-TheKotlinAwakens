package com.hugomatilla.starwars.domain.base

import com.hugomatilla.starwars.domain.exception.DefaultError
import com.hugomatilla.starwars.domain.exception.ErrorBundle
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

/**
 * Created by hugomatilla on 27/02/16.
 */

interface Command<R, T> {
    fun execute(callback: T)
}

interface Command2<R> {
    fun execute(f: () -> R, callback: DefaultCallback<R>) {
        async() {
            try {
                val result = f()
                uiThread {
                    callback.onSuccess(result)
                }
            } catch (e: Exception) {
                uiThread {
                    callback.onError(DefaultError(e))
                }
            }
        }
    }
}

interface DefaultCallback<R> {
    fun onSuccess(result: R)
    fun onError(error: ErrorBundle)
}