package jotlin.lang

import org.testng.annotations.Test
import utils.assert

class LiteralUtilsTest {
    @Test
    fun l() {
        assert(L(1,2,3))
            .equalsValue(mutableListOf(1,2,3))
            .assert("List gave the wrong value")
    }

    @Test
    fun a() {
        assert(a(1,2,3))
            .equalsValue(arrayOf(1,2,3))
            .assert("List gave the wrong value")
    }
}