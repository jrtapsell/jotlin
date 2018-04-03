package jotlin.runner

import java.io.File

/**
 * @author James Tapsell
 */
object Execute: ModeHandler {
    override val description: String
        get() = "Runs a raw file or a set of raw test files"

    override fun validate(args: List<String>): Boolean {
        return args.all{File(it).exists()}
    }

    override fun run(args: List<String>) {
        for (f in args) {
            Executor.execute(File(f).readText())
        }
    }

}