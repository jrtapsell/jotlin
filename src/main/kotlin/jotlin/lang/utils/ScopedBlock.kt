package jotlin.lang.utils

typealias ScopedMonoBlock<T, Z> = ScopedMonoContext<T>.(T)->Z
class ScopedMonoContext<T>(val a: T)
fun <T,U> consume(block: ScopedMonoBlock<T,U>): (T)->U {
    return {input ->
        val context = ScopedMonoContext(input)
        context.block(input)
    }
}

typealias ScopedDualBlock<T, U, Z> = ScopedDualContext<T, U>.()->Z
class ScopedDualContext<T,U>(val a: T, val b: U)
fun <T,U,Z> consume(block: ScopedDualBlock<T,U, Z>): (T, U)->Z {
    return {a,b ->
        val context = ScopedDualContext(a,b)
        context.block()
    }
}

typealias ScopedTripleBlock<T, U, V, Z> = ScopedTripleContext<T, U, V>.()->Z
class ScopedTripleContext<T,U, V>(val a: T, val b: U, val c: V)
fun <T,U, V,Z> consume(block: ScopedTripleBlock<T,U, V, Z>): (T, U, V)->Z {
    return {a,b,c ->
        val context = ScopedTripleContext(a,b,c)
        context.block()
    }
}