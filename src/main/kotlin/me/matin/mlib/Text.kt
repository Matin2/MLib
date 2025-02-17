@file:Suppress("unused")

package me.matin.mlib

private inline val String.pascal get() = first().uppercase() + drop(1).lowercase()
private inline fun String.appendSplit(splitBy: String, append: (Boolean, String) -> String) = when {
	splitBy.isNotEmpty() -> split(splitBy)
	" " in trim() -> split(" ")
	else -> split("(?<=.)(?=\\p{Lu})".toRegex())
}.ifEmpty { return this }.let { buildString { it.forEachIndexed { i, s -> append(append(i == 0, s)) } } }

fun String.camelcase(splitBy: String = "", separator: String = ""): String = appendSplit(splitBy) { first, str ->
	if (first) str.lowercase() else "$separator${str.pascal}"
}

fun String.pascalcase(splitBy: String? = "", separator: String = ""): String = splitBy?.let {
	appendSplit(it) { first, str -> if (first) str.pascal else "$separator${str.pascal}" }
} ?: pascal

fun String.alternatecase(): String = buildString {
	forEachIndexed { i, c -> append(if (i % 2 == 0) c.lowercase() else c.uppercase()) }
}

fun String.lowercase(splitBy: String = "", separator: String): String = appendSplit(splitBy) { first, str ->
	if (first) str.lowercase() else "$separator${str.lowercase()}"
}