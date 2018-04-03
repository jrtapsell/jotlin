@file:Suppress("unused","FunctionName")

package jotlin.lang

import jotlin.lang.utils.ScopedMonoBlock
import jotlin.lang.utils.consume

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

fun <T,U> T.w(block:ScopedMonoBlock<T, U>) = let(consume(block))

infix fun <T, U> T.t(other: U) = this to other
infix fun <T, U, V> Pair<T, U>.t(other: V) = Triple(this.first, this.second, other)
infix fun <T, U, V> T.t(other: Pair<U, V>) = Triple(this, other.first, other.second)