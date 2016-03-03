package com.hugomatilla.starwars.ui.activities

import java.util.concurrent.Executors

/**
 * Created by hugomatilla on 03/03/16.
 */

object JobExecutor {
    val threadPool = Executors.newFixedThreadPool(5)
}