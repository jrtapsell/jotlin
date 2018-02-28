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


operator fun String.invoke(l: Int=0, r:Int=-1, s: Int=1): String {
    fun fix(index: Int): Int {
        if (index < 0) {
            return length + index
        }
        return index
    }

    val start = fix(l)
    val end = fix(r)
    val range = when {
        s <= -1 -> end downTo start step -s
        s >= 1 -> start..end step s
        else -> throw AssertionError("Bad range ($s)")
    }
    return range.joinToString(""){this[it].toString()}
}