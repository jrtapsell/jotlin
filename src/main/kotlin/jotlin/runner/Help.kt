package jotlin.runner

/**
 * @author James Tapsell
 */
object Help: ModeHandler {
    override fun validate(args: List<String>): Boolean {
        return true
    }

    override fun run(args: List<String>) {
        for (i in Mode.values()) {
            System.err.println("${i.name.toLowerCase()} : ${i.handler.description}")
        }
    }

    override val description: String = "Gets this text"
}