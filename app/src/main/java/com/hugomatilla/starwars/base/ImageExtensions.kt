package com.hugomatilla.starwars.base

/**
 * Created by hugomatilla on 22/04/16.
 */

fun String.getImageInPixels(pixels: Int): String {
    return this.replace(Regex("scale-to-width-down/\\d\\d\\d"), "scale-to-width-down/$pixels")
}

fun String.getFullSizeImage(): String {
    return split("/revision")[0]
}