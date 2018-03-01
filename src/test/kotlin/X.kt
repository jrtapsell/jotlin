package jotlin.lang

data class Test(val r: Int, val g: Int, val b: Int, val out: Int)

val test = listOf(
        Test(0, 0, 0, 16),
        Test(95, 135, 0, 64),
        Test(255, 255, 255, 231),
        Test(238, 238, 238, 255),

        Test(90, 133, 140, 66),
        Test(218, 215, 216, 188),
        Test(175, 177, 178, 249),

        Test(175, 0, 155, 127),
        Test(75, 75, 75, 239),
        Test(23, 23, 23, 234),
        Test(115, 155, 235, 111)
)

fun z(it: List<Int>): Int =
(16..255).associate{it to if (it<232)(it-16).let { i ->
            l(0, 95, 135, 175, 215, 255).let { l ->
                l(l[i / 36], l[(i / 6) % 6],
                        l[i % 6])
            }
        } else (8..238 step 10)._l()[it - 232].let { l(it, it, it) }
    }.minBy { (k, v) ->
        (it.zip(v).m { (a, b) -> kotlin.math.abs(a - b) }.sum() * 256) + (256 - k)
    }!!.key

fun main(args: Array<String>) {

    for (i in test) {
        val r = z(listOf(i.r, i.g, i.b))
        println("$i ${i.out} ==> $r")
    }
}