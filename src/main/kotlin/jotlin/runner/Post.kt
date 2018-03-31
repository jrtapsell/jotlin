package jotlin.runner

import java.io.File

/**
 * @author James Tapsell
 */
object Post: ModeHandler {
    override fun validate(args: List<String>): Boolean {
        if (args.size != 1) {
            err("Please provide only a filename")
            return false
        }
        if (!File(args[0]).exists()) {
            err("File not found (${args[0]})")
            return false
        }
        return true
    }

    override fun run(args: List<String>) {
        if (args.size != 1) {
            throw AssertionError("Expected 1 argument (the filepath to the file)")
        }
        val input = File(args[0]).readText()
        println("#[Jotlin](https://github.com/jrtapsell/jotlin), ${input.length} bytes")
        println()
        println("<!-- language: lang-kotlin -->")
        println()
        println(input.lines().map { "    " + it }.joinToString(System.lineSeparator()))
    }
}