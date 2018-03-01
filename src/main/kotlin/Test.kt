package jotlin.lang

fun main(args: Array<String>) {
    var x:(Int)->Char =
        {v->'F'-l(13,170,180,294,300).f{i->i<v}.l()}

    println(x(12))
    println(x(15))
    println(x(301))
    println(x(181))
}