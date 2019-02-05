package ru.hse.spb.fedorov.cli.parsing

interface Parser {
    companion object {
        const val PIPE = '|'
        const val ASSIGNMENT = '='
    }

    fun parse(input: String): List<CommandArguments>
}