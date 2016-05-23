package com.hugomatilla.starwars.domain.base

/**
 * Created by hugomatilla on 27/02/16.
 */

interface Command<R, T> {
    fun execute(callback: T)
}

// Attempt to have a Command interface that run in async.
//interface Command<R> {
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
//}
//
//interface DefaultCallback<R> {
//    fun onSuccess(result: R)
//    fun onError(error: ErrorBundle)
//}