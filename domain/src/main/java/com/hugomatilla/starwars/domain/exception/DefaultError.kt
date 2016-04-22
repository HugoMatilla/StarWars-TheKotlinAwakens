package com.hugomatilla.starwars.domain.exception

/**
 * Created by hugomatilla on 27/03/16.
 */
class DefaultError(override val exception: Exception) : ErrorBundle {

    override val errorMessage: String
        get() = exception.message.toString()

}