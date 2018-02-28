package jotlin.lang

/** Acts as a shortened map. */
infix fun <T,R> Iterable<T>.m(transform: (T)->R) = map(transform)
/** Acts as a shortened map. */
infix fun <T,R> Sequence<T>.m(transform: (T)->R) = map(transform)
/** Acts as a shortened map. */
infix fun <T,R> Array<T>.m(transform: (T)->R) = map(transform)

/** Acts as a shortened indexed map. */
infix fun <T,R> Iterable<T>.m(transform: (Int, T)->R) = mapIndexed(transform)
/** Acts as a shortened indexed map. */
infix fun <T,R> Sequence<T>.m(transform: (Int, T)->R) = mapIndexed(transform)
/** Acts as a shortened indexed map. */
infix fun <T,R> Array<T>.m(transform: (Int, T)->R) = mapIndexed(transform)

/** Acts as a shortened filter. */
infix fun <T> Iterable<T>.f(transform: (T)->Boolean) = filter(transform)
/** Acts as a shortened filter. */
infix fun <T> Sequence<T>.f(transform: (T)->Boolean) = filter(transform)
/** Acts as a shortened filter. */
infix fun <T> Array<T>.f(transform: (T)->Boolean) = filter(transform)

/** Acts as a shortened indexed filter. */
infix fun <T> Iterable<T>.f(transform: (Int, T)->Boolean) = filterIndexed(transform)
/** Acts as a shortened indexed filter. */
infix fun <T> Sequence<T>.f(transform: (Int, T)->Boolean) = filterIndexed(transform)
/** Acts as a shortened indexed filter. */
infix fun <T> Array<T>.f(transform: (Int, T)->Boolean) = filterIndexed(transform)

/** Acts as a shortened fold. */
fun <T,U> Iterable<T>.F(seed: U, transform: (U, T)->U) = fold(seed, transform)
/** Acts as a shortened fold. */
fun <T,U> Sequence<T>.F(seed: U, transform: (U, T)->U) = fold(seed, transform)
/** Acts as a shortened fold. */
fun <T,U> Array<T>.F(seed: U, transform: (U, T)->U) = fold(seed, transform)

/** Acts as a shortened indexed fold. */
fun <T,U> Iterable<T>.F(seed: U, transform: (Int, U, T)->U) = foldIndexed(seed, transform)
/** Acts as a shortened indexed fold. */
fun <T,U> Sequence<T>.F(seed: U, transform: (Int, U, T)->U) = foldIndexed(seed, transform)
/** Acts as a shortened indexed fold. */
fun <T,U> Array<T>.F(seed: U, transform: (Int, U, T)->U) = foldIndexed(seed, transform)

/** Counts each of the distinct items. */
fun <T> Iterable<T>.c() = groupingBy { it }.eachCount()
/** Counts each of the distinct items. */
fun <T> Sequence<T>.c() = groupingBy { it }.eachCount()
/** Counts each of the distinct items. */
fun <T> Array<T>.c() = groupingBy { it }.eachCount()

/** Joins to a string.*/
fun <T> Iterable<T>.j(sep:String=",", block:T.()->Any?={this}) = joinToString(sep) { it.block().toString() }
/** Joins to a string. */
fun <T> Sequence<T>.j(sep:String=",", block:T.()->Any?={this}) = joinToString(sep) { it.block().toString() }
/** Joins to a string. */
fun <T> Array<T>.j(sep:String=",", block:T.()->Any?={this}) = joinToString(sep) { it.block().toString() }

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
fun <T: Any> g(seed: T, nextFun: (T) -> T) = generateSequence(seed, nextFun)