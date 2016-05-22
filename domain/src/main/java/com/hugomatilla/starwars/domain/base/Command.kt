package com.hugomatilla.starwars.domain2.base

import com.hugomatilla.starwars.domain2.exception.ErrorBundle

/**
 * Created by hugomatilla on 27/02/16.
 */

interface Command<R, T> {
    fun execute(callback: T)
}

//interface Command2<R> {
//    fun execute(f: () -> R, callback: DefaultCallback<R>) {
//        async() {
//            try {
//                val result = f()
//                uiThread {
//                    callback.onSuccess(result)
//                }
//            } catch (e: Exception) {
//                uiThread {
//                    callback.onError(DefaultError(e))
//                }
//            }
//        }
//    }
//}e

interface DefaultCallback<R> {
    fun onSuccess(result: R)
    fun onError(error: ErrorBundle)
}