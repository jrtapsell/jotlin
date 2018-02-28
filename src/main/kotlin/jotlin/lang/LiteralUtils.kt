package jotlin.lang

/** Creates a list. */
fun <T> l(vararg items: T) = mutableListOf(*items)

/** Creates an array. */
fun <T> a(vararg items: T) = items