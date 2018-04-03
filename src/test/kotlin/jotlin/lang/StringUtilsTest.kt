package jotlin.lang

import org.testng.annotations.Test
import utils.assert

class StringUtilsTest {
    @Test
    fun pythonishStrings() {
        val test = "abcdef"
        assert(test)
            .equalsValue("abcdef")
            .assert("Broken string")

        assert(test(s=-1))
            .equalsValue("fedcba")
            .assert("Just third failed")

        assert(test(1,-1,2))
            .equalsValue("bdf")
            .assert("All 3 wrong")

        assert(test(-3,-1,1))
            .equalsValue("def")
            .assert("Checks getting part backwards")

        assert(test(s=-2))
            .equalsValue("fdb")
            .assert("Backwards listing broken")

        assert("abc".U)
            .equalsValue("ABC")
            .assert("Upper didn't work")

        assert("ABC".L)
            .equalsValue("abc")
            .assert("Lower didn't work")

        assert(c("abcabd"))
            .equalsValue(mapOf('a' to 2, 'b' to 2, 'c' to 1, 'd' to 1))
            .assert("Count failed")
    }

    @Test(expectedExceptions = [(AssertionError::class)])
    fun testBackwards() {
        "abc"(s=0)
    }
}