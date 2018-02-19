import java.util.Base64
import java.io.ByteArrayOutputStream
import java.util.zip.Deflater

/** Prints whatever is passed to it, followed by a newline. */
fun Any.P(data: Any?) = println(this)

/** Prints multiple items as a list, followed by a newline. */
fun P(vararg data: Any?) = println(data.asList())

/** Prints whatever is passed to it *without* a newline. */
fun Any.p(data: Any?) = print(this)
/** Prints multiple items as a list *without* a newline. */
fun p(vararg data: Any?) = print(data.asList())

/** Deflates input as a Base64 encoded input. */
fun d(input: String): String {
    val deflater = Deflater(9)
    deflater.setInput(Base64.getDecoder().decode(input))
    deflater.finish()
    val buffer = ByteArray(1000)
    val outputStream = ByteArrayOutputStream()
    while (!deflater.finished()) {
        val count = deflater.deflate(buffer)
        outputStream.write(buffer, 0, count)
    }
    val size = deflater.totalOut
    val temp = ByteArray(size - 6)
    /*
     Java gives a header, which nothing else likes.

     https://stackoverflow.com/a/5698317/8041461
    */
    return String(temp, 2, size-6)
}
