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
    fun P() {
        val singleItem = getStdOut {
            P("Hello World")
        }

        assert(singleItem)
            .equalsValue("Hello World\n")
            .assert("P did not give right value")
        val multipleItems = getStdOut {
            P("a", "b", "c")
        }

        assert(multipleItems)
            .equalsValue("a,b,c\n")
            .assert("P did not give right value")
    }

    @Test
    fun p() {
        val singleItem = getStdOut {
            p("Hello World")
        }

        assert(singleItem)
            .equalsValue("Hello World")
            .assert("p did not give right value")
        val multipleItems = getStdOut {
            p("a", "b", "c")
        }

        assert(multipleItems)
            .equalsValue("a,b,c")
            .assert("p did not give right value")
    }
}