@file:Suppress("unused","FunctionName")

package jotlin.lang

/** Creates a regex. */
fun R(input: String) = Regex(input)

/** Replaces the given regex with the given text. */
fun String.r(regex: Regex, text: String) = replace(regex, text)
/** Replaces the given text with the given text. */
fun String.r(find: String, replace: String) = replace(find, replace)
/** Replaces the given regex with the given substitution. */
fun String.r(find: Regex, block:(MatchResult)->CharSequence) = replace(find, block)

val String.L
    get() = toLowerCase()

val String.U
    get() = toUpperCase()