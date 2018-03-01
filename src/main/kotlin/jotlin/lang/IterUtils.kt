package jotlin.lang

import jotlin.lang.utils.ScopedDualBlock
import jotlin.lang.utils.ScopedMonoBlock
import jotlin.lang.utils.ScopedTripleBlock
import jotlin.lang.utils.consume

/** Acts as a shortened map. */
infix fun <T,R> Iterable<T>.m(transform: ScopedMonoBlock<T, R>) = map(consume(transform))
/** Acts as a shortened map. */
infix fun <T,R> Sequence<T>.m(transform: ScopedMonoBlock<T, R>) = map(consume(transform))
/** Acts as a shortened map. */
infix fun <T,R> Array<T>.m(transform: ScopedMonoBlock<T, R>) = map(consume(transform))

/** Acts as a shortened indexed map. */
infix fun <T,R> Iterable<T>.n(transform: ScopedDualBlock<Int, T, R>) = mapIndexed(consume(transform))
/** Acts as a shortened indexed map. */
infix fun <T,R> Sequence<T>.n(transform: ScopedDualBlock<Int, T, R>) = mapIndexed(consume(transform))
/** Acts as a shortened indexed map. */
infix fun <T,R> Array<T>.n(transform: ScopedDualBlock<Int, T, R>) = mapIndexed(consume(transform))

/** Acts as a shortened filter. */
infix fun <T> Iterable<T>.f(transform: ScopedMonoBlock<T, Boolean>) = filter(consume(transform))
/** Acts as a shortened filter. */
infix fun <T> Sequence<T>.f(transform: ScopedMonoBlock<T, Boolean>) = filter(consume(transform))
/** Acts as a shortened filter. */
infix fun <T> Array<T>.f(transform: ScopedMonoBlock<T, Boolean>) = filter(consume(transform))

/** Acts as a shortened indexed filter. */
infix fun <T> Iterable<T>.g(transform: ScopedDualBlock<Int, T, Boolean>) = filterIndexed(consume(transform))
/** Acts as a shortened indexed filter. */
infix fun <T> Sequence<T>.g(transform: ScopedDualBlock<Int, T, Boolean>) = filterIndexed(consume(transform))
/** Acts as a shortened indexed filter. */
infix fun <T> Array<T>.g(transform: ScopedDualBlock<Int, T, Boolean>) = filterIndexed(consume(transform))

/** Acts as a shortened fold. */
fun <T,U> Iterable<T>.F(seed: U, transform: ScopedDualBlock<U, T, U>) = fold(seed, consume(transform))
/** Acts as a shortened fold. */
fun <T,U> Sequence<T>.F(seed: U, transform: ScopedDualBlock<U, T, U>) = fold(seed, consume(transform))
/** Acts as a shortened fold. */
fun <T,U> Array<T>.F(seed: U, transform: ScopedDualBlock<U, T, U>) = fold(seed, consume(transform))

/** Acts as a shortened indexed fold. */
fun <T,U> Iterable<T>.G(seed: U, transform: ScopedTripleBlock<Int, U, T, U>) = foldIndexed(seed, consume(transform))
/** Acts as a shortened indexed fold. */
fun <T,U> Sequence<T>.G(seed: U, transform: ScopedTripleBlock<Int, U, T, U>) = foldIndexed(seed, consume(transform))
/** Acts as a shortened indexed fold. */
fun <T,U> Array<T>.G(seed: U, transform: ScopedTripleBlock<Int, U, T, U>) = foldIndexed(seed, consume(transform))

/** Counts each of the distinct items. */
fun <T> Iterable<T>.c() = groupingBy { it }.eachCount()
/** Counts each of the distinct items. */
fun <T> Sequence<T>.c() = groupingBy { it }.eachCount()
/** Counts each of the distinct items. */
fun <T> Array<T>.c() = groupingBy { it }.eachCount()

/** Joins to a string.*/
fun <T> Iterable<T>.j(sep:String=",", block:ScopedMonoBlock<T, Any?> ={this}) = joinToString(sep) { consume(block)(it).toString() }
/** Joins to a string. */
fun <T> Sequence<T>.j(sep:String=",", block:ScopedMonoBlock<T, Any?> ={this}) = joinToString(sep) { consume(block)(it).toString() }
/** Joins to a string. */
fun <T> Array<T>.j(sep:String=",", block:ScopedMonoBlock<T, Any?> ={this}) = joinToString(sep) { consume(block)(it).toString() }

/** Converts to a sequence. */
fun <T> Iterable<T>._s() = asSequence()
/** Converts to a sequence. */
fun <T> Array<T>._s() = asSequence()

/** Converts to an iterable. */
fun <T> Sequence<T>._i() = asIterable()
/** Converts to an iterable. */
fun <T> Array<T>._i() = asIterable()

/** Converts to an array. */
inline fun <reified T> Sequence<T>._a() = toList().toTypedArray()
/** Converts to an array. */
inline fun <reified T> Iterable<T>._a() = toList().toTypedArray()

/** Converts to a mutable list. */
inline fun <reified T> Iterable<T>._l() = toMutableList()
/** Converts to a mutable list. */
inline fun <reified T> Sequence<T>._l() = toMutableList()
/** Converts to a mutable list. */
inline fun <reified T> Array<T>._l() = toMutableList()

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


fun CharRange.s(size:Int) = step(size)
fun IntRange.s(size:Int) = step(size)
fun LongRange.s(size:Long) = step(size)