@file:Suppress("unused")

package me.matin.mlib

import java.util.*
import kotlin.time.Duration

/** Returns the [Optional.get] of this optional or `null` if empty. */
val <T> Optional<T>.nullable: T? get() = get().takeIf { isPresent }

/**
 * Returns a readable text representation of this duration.
 * @param separator String witch each component will be separated with.
 */
fun Duration.text(separator: String = " "): String = buildString {
    toComponents { days, hours, minutes, seconds, nanos ->
        val millis = nanos / 1_000_000
        addTime(days, "d", separator)
        addTime(hours, "h", separator)
        addTime(minutes, "m", separator)
        addTime(seconds, "s", separator)
        addTime(millis, "ms", separator)
        if (toString().isBlank()) append("0ms")
    }
}.removeSuffix(separator)

private fun <T: Number> StringBuilder.addTime(
    time: T,
    suffix: String,
    separator: String
): StringBuilder? = time.takeIf { it.toInt() > 0 }?.let { append(time.toString() + suffix + separator) }