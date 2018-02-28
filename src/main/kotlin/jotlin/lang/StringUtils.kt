package jotlin.lang

/** Creates a regex. */
fun R(input: String) = Regex(input)
/** Counts the number of times each character appears. */
fun c(input: String) = input.groupingBy { it }.eachCount()

/** Replaces the given regex with the given text. */
fun String.r(regex: Regex, text: String) = replace(regex, text)
/** Replaces the given text with the given text. */
fun String.r(find: String, replace: String) = replace(find, replace)
/** Replaces the given regex with the given substitution. */
fun String.r(find: Regex, block:(MatchResult)->CharSequence) = replace(find, block)