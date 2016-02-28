package com.hugomatilla.starwars.domain

/**
 * Created by hugomatilla on 28/02/16.
 */

interface PostExecutionThread {
    fun post(runnable: Runnable)
}