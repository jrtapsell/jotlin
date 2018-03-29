package jotlin.lang.utils

val <P1, P2> ((P1, P2) -> Boolean).not: (P1, P2) -> Boolean
    get() {
        return {a,b -> !this(a,b)}
    }