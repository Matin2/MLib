@file:Suppress("unused")

package me.matin.mlib

/** Returns the [Lazy.value] of this lazy object or `null` if it's not initialized. */
val <T> Lazy<T>.nullable: T? get() = if (isInitialized()) value else null

/**
 * Calls the specified function [block] with `this` value as its argument if
 * it is initialized and returns `this` value.
 */
fun <T> Lazy<T>.lazyAlso(block: (T) -> Unit): Lazy<T> = also { if (isInitialized()) block(value) }

/**
 * Calls the specified function [block] with `this` value as its receiver if
 * it is initialized and returns `this` value.
 */
fun <T> Lazy<T>.lazyApply(block: T.() -> Unit): Lazy<T> = also { if (isInitialized()) block(value) }

/**
 * Calls the specified function [block] with `this` value as its argument if
 * it is initialized and returns it's result or null if `this` is not initialized.
 */
fun <T, R> Lazy<T>.lazyLet(block: (T) -> R): R? = if (isInitialized()) block(value) else null

/**
 * Calls the specified function [block] with `this` value as its receiver if
 * it is initialized and returns it's result or null if `this` is not initialized.
 */
fun <T, R> Lazy<T>.lazyRun(block: T.() -> R): R? = if (isInitialized()) block(value) else null