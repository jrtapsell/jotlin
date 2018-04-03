package jotlin.runner

import java.io.File
import javax.script.ScriptEngineManager
import javax.script.SimpleScriptContext

/**
 * @author James Tapsell
 */
fun err(message: Any) = System.err.println(message)

object Executor {

    val JOTLIN_HEADER = Executor
        .javaClass
        .getResourceAsStream("/runner/header.kt")
        .reader()
        .use { it.readText() }

    val scriptEngineManager = ScriptEngineManager()
    val kts = scriptEngineManager.getEngineByName("kotlin")

    fun execute(text2: String): Any? {
        val s = JOTLIN_HEADER + text2
        val ctx = SimpleScriptContext()
        return kts.eval(s, ctx)
    }
}