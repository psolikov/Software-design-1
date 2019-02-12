package ru.hse.spb.fedorov.cli.command

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import ru.hse.spb.fedorov.cli.exception.ParserException
import java.lang.StringBuilder

object GrepCommand : GeneralCommand() {
    /**
     * Prints all lines corresponding to the regex.
     * Use "-i" for case insensitive search.
     * "-w" to search only for whole words corresponding to the regex.
     * "-A n" to print n lines after found line.
     */
    override fun execute(args: List<String>, input: String): CommandResult {
        lateinit var arguments: GrepArguments
        try {
            arguments = ArgParser(args.toTypedArray()).parseInto(::GrepArguments)
        } catch (e: Throwable) {
            throw ParserException("Error in parsing grep arguments", e)
        }

        val resultBuilder = StringBuilder()

        arguments.run {
            val pattern = if (onlyWholeWord) "(^|\\s)$pattern($|\\s)" else pattern
            val regexOptions = mutableSetOf<RegexOption>()

            if (caseInsensitive)
                regexOptions.add(RegexOption.IGNORE_CASE)

            val regex = pattern.toRegex(regexOptions)

            val lines = input.split('\n')

            var shouldPrint = 0

            for (line in lines) {
                if (regex.containsMatchIn(line)) {
                    shouldPrint = afterFoundNumber
                    resultBuilder.append(line)
                    resultBuilder.append('\n')
                } else if (shouldPrint > 0) {
                    shouldPrint--
                    resultBuilder.append(line)
                    resultBuilder.append('\n')
                }
            }
        }

        return CommandResult(resultBuilder.toString())
    }

    private class GrepArguments(parser: ArgParser) {
        val caseInsensitive by parser.flagging(
            "-i",
            help = "search case insensitive"
        )
        val onlyWholeWord by parser.flagging(
            "-w",
            help = "search only whole words"
        )
        val afterFoundNumber by parser.storing(
            "-A",
            help = "print n lines after found match line"
        ) { toInt() }.default(0)
        val pattern by parser.positional(
            "PATTERN",
            "pattern for searching"
        )
    }
}