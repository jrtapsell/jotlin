package jotlin.runner

import java.io.File

/**
 * @author James Tapsell
 */
object Post: ModeHandler {
    override val description: String
        get() = "Creates a codegolf submission"

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
        val commitHash = javaClass.`package`.specificationVersion
        val jotlinVersion = javaClass.`package`.implementationVersion
        if (args.size != 1) {
            throw AssertionError("Expected 1 argument (the filepath to the file)")
        }
        val jotlin = JotlinWrapper(File(args[0]))
        val submission = jotlin.body.trim()
        println("# [Jotlin $jotlinVersion](https://github.com/jrtapsell/jotlin), ${submission.length} bytes")
        println("""<!-- METADATA {"version":"$jotlinVersion", "commit":"$commitHash"} -->""")
        println()
        println("## Submission")
        println("<!-- language: lang-kotlin -->")
        println()
        println(submission.asBlock())
        println()
        println("## Test code")
        println("<!-- language: lang-kotlin -->")
        println()
        println(jotlin.runnableCode.asBlock())


    }

    private fun String.asBlock() =
        lines().map { "    " + it }.joinToString(System.lineSeparator())
}