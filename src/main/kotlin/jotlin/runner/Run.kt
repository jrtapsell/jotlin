package jotlin.runner

import java.io.File
import javax.script.ScriptEngineManager

/**
 * @author James Tapsell
 */
object Run: ModeHandler {
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
        val scriptEngineManager = ScriptEngineManager()
        val kts = scriptEngineManager.getEngineByName("kotlin")

        val header = javaClass
            .getResourceAsStream("/runner/header.kt")
            .bufferedReader()
            .use { it.readText() }

        val text = File(args[0]).readText()

        kts.eval("$header\n$text")
    }

}