package com.hugomatilla.starwars.domain.exception

/**
 * Created by hugomatilla on 27/02/16.
 */

interface ErrorBundle {
    val exception: Exception

    val errorMessage: String
}