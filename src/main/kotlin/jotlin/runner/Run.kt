package jotlin.runner

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.google.gson.GsonBuilder
import org.jetbrains.kotlin.utils.addToStdlib.ifNotEmpty
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream

/**
 * @author James Tapsell
 */

typealias DataFile = List<Map<String, Any>>
object Run: ModeHandler {
    override val description: String
        get() = "Runs a configured case or a set of configured cases"
    val builder = GsonBuilder().create()!!
    val mapper = ObjectMapper().registerModule(KotlinModule())
    override fun validate(args: List<String>): Boolean {
        if (args.isEmpty()) {
            err("Please provide a metadata file")
            return false
        }
        args.map { File(it) }
            .filter { !it.exists() || it.extension != "json" }
            .ifNotEmpty {
                err("Files not found ($this)")
                return false
            }
        return true
    }

    class MemoryPrintStream(val original: PrintStream) {
        val backing = ByteArrayOutputStream()
        val stream = PrintStream(backing)

        val output: String get() {
            return String(backing.toByteArray())
        }
    }

    override fun run(args: List<String>) {

        for (file in args) {
            println("Running $file")
            val wrapper = JotlinWrapper(File(file))

            withFakedIO { out, err ->
                val code = wrapper.runnableCode
                Executor.execute(code)

                when (wrapper.mode) {
                    ChallengeType.fixed -> {
                        if (out.output != wrapper.expectedOutput) {
                            throw AssertionError("Unexpected output")
                        }
                        out.original.print(TestUtils.tick)
                    }
                    ChallengeType.battery -> {
                        out.original.print(out.output)
                        err.original.print(err.output)
                    }
                }

            }
            println()
        }
    }

}

fun withFakedIO(block: (Run.MemoryPrintStream, Run.MemoryPrintStream)->Unit): Pair<Run.MemoryPrintStream, Run.MemoryPrintStream> {
    val out = Run.MemoryPrintStream(System.out)
    val err = Run.MemoryPrintStream(System.err)

    System.setOut(out.stream)
    System.setErr(err.stream)

    try {
        block(out, err)
    } finally {
        System.setOut(out.original)
        System.setErr(err.original)
    }
    return out to err
}