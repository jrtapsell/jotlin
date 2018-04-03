@file:Suppress("unused","FunctionName")

package jotlin.lang

import java.util.Base64
import java.io.ByteArrayOutputStream
import java.util.zip.Inflater

/** Prints whatever is passed to it, followed by a newline. */
fun P(data: Any?) = println(data)

/** Prints multiple items. */
fun P(vararg data: Any?) = println(data.j(","))

/** Prints whatever is passed to it *without* a newline. */
fun p(data: Any?) = print(data)
/** Prints multiple items *without* a newline. */
fun p(vararg data: Any?) = print(data.j(","))

/** Deflates input as a Base64 encoded input. */
fun d(input: String): String {
    val deflater = Inflater()
    deflater.setInput(Base64.getDecoder().decode(input))
    val buffer = ByteArray(1024)
    val outputStream = ByteArrayOutputStream()
    do {
        val count = deflater.inflate(buffer)
        outputStream.write(buffer, 0, count)
    } while (count != 0)
    val temp = outputStream.toByteArray()
    return String(temp)
}