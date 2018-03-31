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
    fun w() {
        val x = 100
        x.w{
            assert(a)
                .isValue(x)
                .assert("With gave wrong value")
        }
    }

    @Test
    fun t() {
        val firstPair = 1 t 2
        val secondPair = 2 t 3
        assert(firstPair)
            .equalsValue(1 to 2)
            .assert("Pair creation failed")

        val firstTriple = firstPair t 3
        val secondTriple = 1 t secondPair

        assert(firstTriple)
            .equalsValue(Triple(1,2,3))
            .assert("End triple failed")

        assert(secondTriple)
            .equalsValue(Triple(1,2,3))
            .assert("Start triple failed")
    }
}