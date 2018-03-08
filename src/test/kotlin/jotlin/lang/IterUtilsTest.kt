package jotlin.lang

import org.testng.annotations.Test
import utils.assert

/**
 * @author James Tapsell
 */
class IterUtilsTest {
    @Test
    fun testSlices() {
        val original = listOf("a", "b", "c", "d")
        assert(original(s=-1))
            .equalsValue(listOf("d", "c", "b", "a"))
            .assert("Trying backwards")

        assert(original(l=1))
            .equalsValue(listOf("b", "c", "d"))
            .assert("Skipping the first")

        assert(original(r=-2))
            .equalsValue(listOf("a", "b", "c"))
            .assert("Skipping the last")
    }

    @Test
    fun w() {
        assert(l(1,2,3,4,5).w(3))
            .equalsValue(l(l(1,2,3), l(2,3,4), l(3,4,5)))
            .assert("Testing window function")
    }

    @Test
    fun G() {
        val f = (0..2).F(0){b+(c*a)}
        assert(f)
            .equalsValue(5)
            .assert("Indexed fold failed")
    }

    @Test
    fun M() {
        val m = l("a", "b", "c").m { "$a$b" }
        assert(m)
            .equalsValue(l("0a", "1b", "2c"))
            .assert("Map indexed failed")
    }

    @Test
    fun a() {
        val list = l(1,2,3,4)
        val array = a(1,2,3,4)

        val iterable = list.asIterable()
        val sequence = list.asSequence()

        assert(iterable.a)
            .equalsValue(array)
            .assert("Iterable to array works")

        assert(sequence.a)
            .equalsValue(array)
            .assert("Sequence to array works")
    }

    @Test
    fun r() {
        val items = 10 r{10 r{1}}

        assert(items.map { it.sum() }.sum())
            .equalsValue(100)
            .assert("Total is not 100")
    }

    @Test
    fun C() {
        val items = l(1,2,3,4,5)
        assert(items.C(2))
            .equalsValue(l(l(1,2), l(3,4), l(5)))
            .assert("Chunked gave wrong answers")
    }

    @Test
    fun e() {
        var total = 0
        (0..4).e{total+=b}
        assert(total)
            .equalsValue(10)
            .assert("For each failed")
    }
}