@file:Suppress("unused")

package me.matin.mlib

import java.util.*
import kotlin.time.Duration

/** Returns the [Optional.get] of this optional or `null` if empty. */
val <T> Optional<T>.nullable: T? get() = get().takeIf { isPresent }

/**
 * Returns a readable text representation of this duration.
 *
 * @param separator String witch each component will be separated with.
 */
fun Duration.text(separator: String = " "): String = buildString {
    toComponents { days, hours, minutes, seconds, nanos ->
        val millis = nanos / 1_000_000
        addTime(days, "d", separator)
        addTime(hours, "h", separator)
        addTime(minutes, "m", separator)
        addTime(seconds, "s", separator)
        addTime(millis, "ms", "")
        if (toString().isBlank()) append("0ms")
    }
}

private inline fun <reified T: Number> StringBuilder.addTime(time: T, suffix: String, separator: String) {
    time.takeIf { it.toInt() > 0 }?.let { append("$time$suffix$separator") }
}

inline fun <T> Iterable<T>.filterAll(predicates: (T) -> Pair<Boolean, Boolean>): Boolean {
    var result: Boolean? = null
    forEach {
        val (filter, all) = predicates(it)
        if (filter) result = all && result != false
    }
    return result == true
}

inline fun <T> Iterable<T>.filterAny(predicates: (T) -> Pair<Boolean, Boolean>): Boolean {
    var result = false
    forEach {
        val (filter, all) = predicates(it)
        if (filter) result = all || result
    }
    return result
}

inline fun <T> Iterable<T>.filterNone(predicates: (T) -> Pair<Boolean, Boolean>): Boolean = !filterAny(predicates)