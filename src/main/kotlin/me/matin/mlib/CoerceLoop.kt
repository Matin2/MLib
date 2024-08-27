@file:Suppress("unused", "DuplicatedCode")

package me.matin.mlib

fun Int.coerceLoopIn(range: ClosedRange<Int>): Int {
    require(!range.isEmpty()) { "Cannot coerce loop value to an empty range: $range." }
    if (this in range) return this
    val min = range.start
    val size = range.endInclusive - min + 1
    return minus(min).rem(size).plus(min).let {
        if (it < 0) it + size else it
    }
}

fun Long.coerceLoopIn(range: ClosedRange<Long>): Long {
    require(!range.isEmpty()) { "Cannot coerce loop value to an empty range: $range." }
    if (this in range) return this
    val min = range.start
    val size = range.endInclusive - min + 1
    return minus(min).rem(size).plus(min).let {
        if (it < 0) it + size else it
    }
}

fun Int.coerceLoopIn(min: Int, max: Int): Int = coerceLoopIn(min..max)
fun Long.coerceLoopIn(min: Long, max: Long): Long = coerceLoopIn(min..max)
