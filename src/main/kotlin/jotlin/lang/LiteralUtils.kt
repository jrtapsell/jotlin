package jotlin.lang

/** Creates a list. */
fun <T> l(vararg items: T) = mutableListOf(*items)

/** Creates an array. */
inline fun <reified T> a(vararg items: T) = items.map { it }.toTypedArray()