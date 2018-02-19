/** Acts as a shortened map. */
infix fun <T,R> Iterable<T>.m(transform: (T)->R) = map(transform)
infix fun <T,R> Sequence<T>.m(transform: (T)->R) = map(transform)
infix fun <T,R> Array<T>.m(transform: (T)->R) = map(transform)

infix fun <T> Iterable<T>.f(transform: (T)->Boolean) = filter(transform)
infix fun <T> Sequence<T>.f(transform: (T)->Boolean) = filter(transform)
infix fun <T> Array<T>.f(transform: (T)->Boolean) = filter(transform)

fun <T> Iterable<T>.c() = groupingBy { it }.eachCount()
fun <T> Sequence<T>.c() = groupingBy { it }.eachCount()
fun <T> Array<T>.c() = groupingBy { it }.eachCount()

fun <T> Iterable<T>.j(sep:String=",", block:T.()->Any?={this}) = joinToString(sep) { it.block().toString() }
fun <T> Sequence<T>.j(sep:String=",", block:T.()->Any?={this}) = joinToString(sep) { it.block().toString() }
fun <T> Array<T>.j(sep:String=",", block:T.()->Any?={this}) = joinToString(sep) { it.block().toString() }

fun <T> Iterable<T>._s() = asSequence()
fun <T> Array<T>._s() = asSequence()

fun <T> Sequence<T>._i() = asIterable()
fun <T> Array<T>._i() = asIterable()

inline fun <reified T> Sequence<T>._a() = toList().toTypedArray()
inline fun <reified T> Iterable<T>._a() = toList().toTypedArray()