package jotlin.runner

enum class Mode(val handler: ModeHandler) {
    RUN(Run),
    POST(Post),
    EXECUTE(Execute),
    HELP(Help);

    companion object {
        val safeValues = values()
            .associate { it.name.toLowerCase() to it }
    }
}