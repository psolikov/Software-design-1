package ru.hse.spb.fedorov.cli.executor

import ru.hse.spb.fedorov.cli.parsing.CommandArguments

interface CommandExecutor {
    fun execute(commands: List<CommandArguments>): String
    fun execute(command: CommandArguments): String
}