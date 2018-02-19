/**
 * Converts to an Integer.
 * @param radix
 *  The radix to use _(defaults to 10)_
 */
fun Any?.i(radix:Int=10) = toString().toInt(radix)

/**
 * Converts to a String.
 */
fun Any?.s() = toString()