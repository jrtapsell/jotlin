package jotlin.lang

import org.testng.annotations.Test
import utils.assert
import utils.getStdOut

class IOUtilsTest {
    @Test
    fun d() {
        val input = d("eJzzSM3JyVcIzy/KSQEAGAsEHQ==")

        assert(input)
            .equalsValue("Hello World")
            .assert("Decompression failed")
    }

    @Test
    fun P1() {
        val output = getStdOut {
            P("Hello World")
        }

        assert(output)
            .equalsValue("Hello World\n")
            .assert("P did not give right value")
    }

    @Test
    fun P2() {
        val output = getStdOut {
            P("a", "b", "c")
        }

        assert(output)
            .equalsValue("a,b,c\n")
            .assert("P did not give right value")
    }
}