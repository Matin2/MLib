@file:Suppress("unused")

package me.matin.mlib

import java.math.RoundingMode
import java.text.DecimalFormat

inline val Double.precision: Int get() = toString().trimStart { it.isDigit() }.length - 1
inline val Float.precision: Int get() = toString().trimStart { it.isDigit() }.length - 1

/**
 * Rounds this double with the given precision.
 *
 * @param precision Maximum fraction digits this double should have.
 * @see Double.precision
 */
fun Double.round(precision: Int): Double = DecimalFormat().run {
    roundingMode = RoundingMode.HALF_UP
    maximumFractionDigits = precision
    format(this@round).toDouble()
}

/**
 * Rounds this float with the given precision.
 *
 * @param precision Maximum fraction digits this float should have.
 * @see Float.precision
 */
fun Float.round(precision: Int): Float = DecimalFormat().run {
    roundingMode = RoundingMode.HALF_UP
    maximumFractionDigits = precision
    format(this@round).toFloat()
}