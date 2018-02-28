package utils

import org.testng.Assert
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.io.PrintWriter

class NewAssertion<T>(val value: T) {
    fun equalsValue(expected: T): Assertion {
        return Assertion { message -> Assert.assertEquals(value, expected, message) }
    }
}

fun <T> assert(data: T): NewAssertion<T> = NewAssertion(data)

class Assertion(val action: (String)->Unit) {
    fun assert(message: String) = action.invoke(message)
}

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