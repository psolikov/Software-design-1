package ru.hse.spb.fedorov.cli.environment

import ru.hse.spb.fedorov.cli.command.Command
import ru.hse.spb.fedorov.cli.command.CommandResult

interface Environment {
    companion object {
        const val CURRENT_DIRECTORY_PATH = "CDPath"
    }

    fun setCommand(name: String, command: Command)

    fun executeCommand(name: String, args: List<String>, input: String): CommandResult

    fun setVariable(name: String, value: String)

    fun getVariable(name: String): String
}