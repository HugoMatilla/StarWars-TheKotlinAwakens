package com.hugomatilla.starwars.data.cloud.exception

import com.hugomatilla.starwars.domain.exception.ErrorBundle

/**
 * Created by hugomatilla on 28/02/16.
 */

class RepositoryError(override val exception: Exception) : ErrorBundle {

    override val errorMessage: String
        get() = exception.message as String
}