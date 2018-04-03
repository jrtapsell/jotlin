package jotlin.runner

import kotlin.system.exitProcess

interface ModeHandler {
    fun validate(args: List<String>): Boolean
    fun run(args: List<String>)
    val description: String
}

object EntryPoint {
    @JvmStatic
    fun main(args: Array<String>) {
        val listArgs = args.toList()

        val instruction = listArgs
            .getOrNull(0)
            ?.let { Mode.safeValues.get(it.toLowerCase()) }
            ?.handler

        if (instruction == null) {
            System.err.println("Mode needs to be provided, or use help to get description")
            exitProcess(1)
        }

        val handlerArgs = listArgs.drop(1)

        if (instruction.validate(handlerArgs)) {
            instruction.run(handlerArgs)
        } else {
            System.err.println("Bad command")
        }
    }
}