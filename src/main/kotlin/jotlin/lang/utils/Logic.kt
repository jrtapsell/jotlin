@file:Suppress("unused","FunctionName")

package jotlin.lang.utils

fun String.example() {

}

val <P1, P2> ((P1, P2) -> Boolean).not: (P1, P2) -> Boolean
    get() {
        return {a,b -> !this(a,b)}
    }