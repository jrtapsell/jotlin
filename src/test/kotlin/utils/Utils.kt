package utils

import org.testng.Assert
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.io.PrintWriter

typealias Assertion = (String)->Unit
class NewAssertion<T>(private val value: T) {
    private val assertionsList = mutableListOf<Assertion>()
    fun equalsValue(expected: T): NewAssertion<T> {
        assertionsList.add { message -> Assert.assertEquals(value, expected, message) }
        return this
    }

    fun isValue(expected: T): NewAssertion<T> {
        assertionsList.add { message -> Assert.assertSame(value, expected, message)}
        return this
    }

    fun assert(message: String) = assertionsList.forEach{it(message)}
}

fun <T> assert(data: T): NewAssertion<T> = NewAssertion(data)

fun getStdOut(block: ()->Unit): String {
    val oldOut = System.out
    ByteArrayOutputStream().use { outBuffer ->
        PrintStream(outBuffer).use { outStream ->
            System.setOut(PrintStream(outBuffer))
            block()
            outStream.flush()
            System.setOut(oldOut)
            return String(outBuffer.toByteArray())
        }
    }
}