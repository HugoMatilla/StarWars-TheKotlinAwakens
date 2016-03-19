package com.hugomatilla.starwars.domain.base

/**
 * Created by hugomatilla on 27/02/16.
 */

interface Command<T> {
    fun execute(callback: T)
}
