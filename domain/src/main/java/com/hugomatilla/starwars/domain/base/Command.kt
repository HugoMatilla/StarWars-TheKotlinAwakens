package com.hugomatilla.starwars.domain.base

/**
 * Created by hugomatilla on 27/02/16.
 */

interface Command<R, T> {
    fun execute(callback: T)
    fun execute(f: () -> R, callback: T)
}
