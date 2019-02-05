package ru.hse.spb.fedorov.cli.command

import java.nio.charset.Charset
import java.nio.file.Paths

object WcCommand : GeneralCommand() {
    override fun execute(args: List<String>, input: String): CommandResult {
        if (args.isEmpty()) return CommandResult(calculateStatistics(input))

        return CommandResult(args.joinToString("\n") { calculateStatistics(it) })
    }

    private fun calculateStatistics(fileName: String): String {
        val bytes = Paths.get(fileName).toFile().readBytes()
        val text = bytes.toString(Charset.defaultCharset())

        val wordsNumber = text.split("\\s".toRegex()).filter { it.isNotEmpty() }.size

        return "${1 + text.count({ it == '\n' })} ${wordsNumber} ${bytes.size}"
    }
}