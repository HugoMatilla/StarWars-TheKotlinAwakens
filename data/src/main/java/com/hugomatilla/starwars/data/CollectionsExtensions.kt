package com.hugomatilla.starwars.data

/**
 * Created by hugomatilla on 20/03/16.
 */

fun <K, V : Any> MutableMap<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
        map({ Pair(it.key, it.value!!) }).toTypedArray()

fun Collection<Any>?.isEmptyOrNull(): Boolean = this == null || this.isEmpty()
