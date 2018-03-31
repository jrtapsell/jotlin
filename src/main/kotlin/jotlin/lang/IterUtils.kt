@file:Suppress("unused","FunctionName")

package jotlin.lang

import jotlin.lang.utils.ScopedDualBlock
import jotlin.lang.utils.ScopedMonoBlock
import jotlin.lang.utils.ScopedTripleBlock
import jotlin.lang.utils.consume
import jotlin.lang.utils.not

typealias L<T> = MutableList<T>

/** Acts as a shortened indexed map. */
infix fun <T,R> Iterable<T>.m(transform: ScopedDualBlock<Int, T, R>) = mapIndexed(consume(transform))
/** Acts as a shortened indexed map. */
infix fun <T,R> Sequence<T>.m(transform: ScopedDualBlock<Int, T, R>) = mapIndexed(consume(transform))
/** Acts as a shortened indexed map. */
infix fun <T,R> Array<T>.m(transform: ScopedDualBlock<Int, T, R>) = mapIndexed(consume(transform))

/** Acts as a shortened indexed filter. */
infix fun <T> Iterable<T>.f(transform: ScopedDualBlock<Int, T, Boolean>) = filterIndexed(consume(transform))
/** Acts as a shortened indexed filter. */
infix fun <T> Sequence<T>.f(transform: ScopedDualBlock<Int, T, Boolean>) = filterIndexed(consume(transform))
/** Acts as a shortened indexed filter. */
infix fun <T> Array<T>.f(transform: ScopedDualBlock<Int, T, Boolean>) = filterIndexed(consume(transform))

/** Filters for a certain value. */
infix fun <T> Iterable<T>.f(value: T) = filter { value == it }
/** Filters for a certain value. */
infix fun <T> Sequence<T>.f(value: T) = filter { value == it }
/** Filters for a certain value. */
infix fun <T> Array<T>.f(value: T) = filter { value == it }

/** Acts as a shortened inverted indexed filter. */
infix fun <T> Iterable<T>.n(transform: ScopedDualBlock<Int, T, Boolean>) = filterIndexed(consume(transform).not)
/** Acts as a shortened inverted indexed filter. */
infix fun <T> Sequence<T>.n(transform: ScopedDualBlock<Int, T, Boolean>) = filterIndexed(consume(transform).not)
/** Acts as a shortened inverted indexed filter. */
infix fun <T> Array<T>.n(transform: ScopedDualBlock<Int, T, Boolean>) = filterIndexed(consume(transform).not)

/** Filters for not a certain value. */
infix fun <T> Iterable<T>.n(value: T) = filter { value != it }
/** Filters for not a certain value. */
infix fun <T> Sequence<T>.n(value: T) = filter { value != it }
/** Filters for not a certain value. */
infix fun <T> Array<T>.n(value: T) = filter { value != it }

/** Acts as a shortened indexed fold. */
fun <T,U> Iterable<T>.F(seed: U, transform: ScopedTripleBlock<Int, U, T, U>) = foldIndexed(seed, consume(transform))
/** Acts as a shortened indexed fold. */
fun <T,U> Sequence<T>.F(seed: U, transform: ScopedTripleBlock<Int, U, T, U>) = foldIndexed(seed, consume(transform))
/** Acts as a shortened indexed fold. */
fun <T,U> Array<T>.F(seed: U, transform: ScopedTripleBlock<Int, U, T, U>) = foldIndexed(seed, consume(transform))

/** Counts each of the distinct items. */
fun <T> Iterable<T>.c() = groupingBy { it }.eachCount()
/** Counts each of the distinct items. */
fun <T> Sequence<T>.c() = groupingBy { it }.eachCount()
/** Counts each of the distinct items. */
fun <T> Array<T>.c() = groupingBy { it }.eachCount()

/** Joins to a string.*/
fun <T> Iterable<T>.j(sep:String=",", block:ScopedMonoBlock<T, Any?> ={a}) = joinToString(sep) { consume(block)(it).toString() }
/** Joins to a string. */
fun <T> Sequence<T>.j(sep:String=",", block:ScopedMonoBlock<T, Any?> ={a}) = joinToString(sep) { consume(block)(it).toString() }
/** Joins to a string. */
fun <T> Array<T>.j(sep:String=",", block:ScopedMonoBlock<T, Any?> ={a}) = joinToString(sep) { consume(block)(it).toString() }

/** Converts to a sequence. */
val <T> Iterable<T>.s
    get() = asSequence()
/** Converts to a sequence. */
val <T> Array<T>.s
    get() = asSequence()

/** Converts to an iterable. */
val <T> Sequence<T>.i
    get() = asIterable()
/** Converts to an iterable. */
val <T> Array<T>.i
    get() = asIterable()

/** Converts to an array. */
inline val <reified T> Sequence<T>.a
    get() = toList().toTypedArray()

/** Converts to an array. */
inline val <reified T> Iterable<T>.a
    get() = toList().toTypedArray()

/** Converts to a mutable list. */
val <T> Iterable<T>.l
    get() = toMutableList()
/** Converts to a mutable list. */
val <T> Sequence<T>.l
    get() = toMutableList()
/** Converts to a mutable list. */
val <T> Array<T>.l
    get() = toMutableList()

/** Generates a sequence. */
fun <T: Any> g(nextFun: () -> T) = generateSequence(nextFun)
/** Generates a sequence. */
fun <T: Any> g(seed: T, nextFun: ScopedMonoBlock<T, T>) = generateSequence(seed, consume(nextFun))

operator fun <T> List<T>.invoke(l: Int=0, r:Int=-1, s: Int=1): List<T> {
    fun fix(index: Int): Int {
        if (index < 0) {
            return count() + index
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
    return range.map { get(it) }
}

operator fun <T> Iterable<T>.invoke(l: Int=0, r:Int=-1, s: Int=1) = toList()(l,r,s).asIterable()
operator fun <T> Sequence<T>.invoke(l: Int=0, r:Int=-1, s: Int=1) = toList()(l,r,s).asSequence()
inline operator fun <reified T> Array<T>.invoke(l: Int=0, r:Int=-1, s: Int=1) = toList()(l,r,s).toTypedArray()

fun <T> Iterable<T>.l() = count()
fun <T> Sequence<T>.l() = count()
fun <T> Array<T>.l() = count()


infix fun CharRange.s(size:Int) = step(size)
infix fun IntRange.s(size:Int) = step(size)
infix fun LongRange.s(size:Long) = step(size)

infix fun <T> List<T>.w(windowSize:Int) = (0..(size-windowSize)).map { subList(it, it + windowSize) }

infix fun <T> Iterable<T>.w(windowSize: Int) = toList().w(windowSize).asIterable()
infix fun <T> Sequence<T>.w(windowSize: Int) = toList().w(windowSize).asSequence()
inline infix fun <reified T> Array<T>.w(windowSize: Int) = toList().w(windowSize).toTypedArray()

fun <T> List<T>.x(other:List<T> =this) = this.flatMap { first -> other.map {second -> first to second  } }

fun <T> Iterable<T>.x(other:Iterable<T> =this) = l.x(other.l)
fun <T> Sequence<T>.x(other:Sequence<T> =this) = l.x(other.l)
inline fun <reified T> Array<T>.x(other:Array<T> =this) = l.x(other.l)

infix fun <T> Int.r(item: T): MutableList<T> = (1..this).map { item }.toMutableList()
infix fun <T> Int.r(item: ScopedMonoBlock<Int, T>): MutableList<T> = (1..this).map { consume(item)(it) }.toMutableList()

fun <T> Iterable<T>.C(size: Int) = chunked(size)
fun <T> Sequence<T>.C(size: Int) = chunked(size)
fun <T> Array<T>.C(size: Int) = l.chunked(size)

fun <T> Iterable<T>.e(block: ScopedDualBlock<Int, T, Unit>) = forEachIndexed(consume(block))
fun <T> Sequence<T>.e(block: ScopedDualBlock<Int, T, Unit>) = forEachIndexed(consume(block))
fun <T> Array<T>.e(block: ScopedDualBlock<Int, T, Unit>) = forEachIndexed(consume(block))

fun <T> Iterable<T>.z(other: Iterable<T>) = zip(other)
fun <T> Sequence<T>.z(other: Sequence<T>) = zip(other)
fun <T> Array<T>.z(other: Array<T>) = zip(other)
