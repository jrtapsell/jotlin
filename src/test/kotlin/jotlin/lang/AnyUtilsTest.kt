package jotlin.lang

import org.testng.annotations.Test
import utils.assert

class AnyUtilsTest {
    @Test
    fun i() {
        for (i in 0..100) {
            val textual = i.toString(10)

            assert(textual.i())
                .equalsValue(i)
                .assert("Parsing string failed")

            assert(i.i())
                .equalsValue(i)
                .assert("Int to int changed value")
        }
    }

    @Test
    fun s() {
        for (i in 0..100) {
            val textual = i.toString(10)

            assert(i.s())
                .equalsValue(textual)
                .assert("Bad String value")

            assert(textual.s())
                .equalsValue(textual)
                .assert("S changed string value")

            val binary = i.toString(2)
            assert(i.s(2))
                .equalsValue(binary)
                .assert("Custom regex failed")

            val long = i.toLong()

            assert(long.s(2))
                .equalsValue(binary)
                .assert("Long conversion failed")
        }
    }

    @Test
    fun l() {
        var x=0
        val i = 100
        i.l { x = a }
        assert(x)
            .equalsValue(100)
            .assert("Let did not work")
    }
}