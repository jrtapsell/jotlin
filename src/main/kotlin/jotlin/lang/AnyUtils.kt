package jotlin.lang
/**
 * Converts to an Integer.
 *
 *      > "1".i()
 *      < 1
 * @param radix
 *  The radix to use _(defaults to 10)_
 */
fun Any?.i(radix:Int=10) = toString().toInt(radix)

/**
 * Converts to a String.
 *
 *      > 1.s()
 *      < "1"
 */
fun Any?.s() = toString()

/** Allows for the radix of s() to be set.
 *
 *      > 1.s()
 *      < "1"
 *      > 10.s(2)
 *      < 2
 */
fun Int.s(radix: Int=10) = toString(radix)
/**
 * Allows for the radix of s() to be set.
 *
 *      > 1L.s()
 *      < "1"
 *      > 10L.s(2)
 *      < 2
 */
fun Long.s(radix: Int=10) = toString(radix)