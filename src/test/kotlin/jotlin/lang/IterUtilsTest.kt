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
}