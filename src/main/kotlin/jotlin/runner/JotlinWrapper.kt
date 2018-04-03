package jotlin.runner

import java.io.File


data class TypeDef(
    val `in`: List<String>,
    val out: String
)

enum class ChallengeType {
    fixed, battery
}
data class MetaData(
    val type: ChallengeType,
    val header: String?,
    val body: String,
    val footer: String?,
    val output: String?,
    val data: String?,
    val testFunction: String?,
    val types: TypeDef?
)

/**
 * @author James Tapsell
 */
class JotlinWrapper(val metaFile: File) {
    val metadata = metaFile.reader().use { Run.mapper.readValue(it, MetaData::class.java)}

    fun readResolved(name: String?): String = name?.let{ metaFile.resolveSibling(it)}?.readText() ?: ""

    val header = readResolved(metadata.header)
    val body = readResolved(metadata.body)
    val footer = readResolved(metadata.footer)

    val expectedOutput = readResolved(metadata.output)

    val realText = listOf(header, body, footer).filter { it.isNotEmpty() }.joinToString("\n")

    val mode = metadata.type

    val runnableCode: String get() {
        if (mode == ChallengeType.fixed) return realText
        if (mode != ChallengeType.battery) throw AssertionError()
        val text = readResolved(metadata.data)
        val data = Run.builder.fromJson(text, List::class.java) as DataFile

        val T = "\t"
        val types = metadata.types!!

        val inputTypes = types.`in`
        val outputType = types.out

        val extension = "data class Test(" + inputTypes.mapIndexed { index, s ->
            "val input$index: $s"
        }.joinToString(",") + ", val output: $outputType)"

        var testRunner = "val tests = listOf<Test>(\n"
        testRunner += data
            .map {
                val inputs = (it["in"] as List<Any?>)
                    .mapIndexed { index, any -> any.wrap(inputTypes[index]) }
                    .joinToString(",")
                val outputs = it["out"].wrap(types.out)
                "\tTest($inputs,$outputs)"
            }
            .joinToString(",\n")
        testRunner += "\n)"

        val actingMethod = """
                    |for (r in tests) {
                    |${T}val result = ${metadata.testFunction}
                    |${T}if (result != r.output) {
                    |$T${T}error("Error during ${"$"}r, expected ${"$"}{r.output}, got ${"$"}result")
                    |$T} else {
                    |$T${T}print(jotlin.runner.TestUtils.tick)
                    |$T}
                    |}
                    """.trimMargin()

        return "$realText\n$extension\n\n$testRunner\n\n$actingMethod"
    }
}


private fun Any?.wrap(targetType: String): String {
    if (this == null) return "null"
    when (targetType) {
        "String" -> return "\"\"\"" + this.toString().replace("$", "\${D}") + "\"\"\""
        "Int" -> return this.toString().toDouble().toInt().toString()
        "Char" -> return "'" + this.toString() + "'"
        "Boolean" -> return this.toString()
    }

    if (targetType.startsWith("List<")) {
        val innerTargetType = targetType.drop(5).dropLast(1)
        if (this !is List<Any?>) throw AssertionError()
        return "listOf(" + this.joinToString (","){ it.wrap(innerTargetType) } + ")"
    }

    throw AssertionError(targetType)

}