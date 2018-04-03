@file:Suppress("unused","FunctionName")

package jotlin.lang

/** Creates a list. */
fun <T> L(vararg items: T) = mutableListOf(*items)

/** Creates an array. */
inline fun <reified T> A(vararg items: T) = items.map { it }.toTypedArray()