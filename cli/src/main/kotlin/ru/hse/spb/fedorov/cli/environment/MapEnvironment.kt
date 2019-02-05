package ru.hse.spb.fedorov.cli.environment

import ru.hse.spb.fedorov.cli.command.Command
import ru.hse.spb.fedorov.cli.command.CommandResult
import ru.hse.spb.fedorov.cli.command.executeWithEnvironment
import ru.hse.spb.fedorov.cli.environment.Environment.Companion.CURRENT_DIRECTORY_PATH
import ru.hse.spb.fedorov.cli.exception.CommandShellException
import java.io.File
import java.nio.charset.Charset
import java.nio.file.InvalidPathException
import java.nio.file.Paths
import java.nio.file.Path

class MapEnvironment : Environment {
    private val variables: MutableMap<String, String> = mutableMapOf()
    private val commands: MutableMap<String, Command> = mutableMapOf()

    override fun setCommand(name: String, command: Command) {
        commands[name] = command
    }

    override fun executeCommand(name: String, args: List<String>, input: String): CommandResult {
        return commands[name]?.executeWithEnvironment(args, input, this) ?: executeNonDefinedCommand(name, args, input)
    }

    override fun setVariable(name: String, value: String) {
        variables[name] = value
    }

    override fun getVariable(name: String): String = variables.getOrDefault(name, "")

    private fun executeNonDefinedCommand(name: String, args: List<String>, input: String): CommandResult {
        val currentPath = getCurrentPath().toString() + File.separator

        val process = Runtime.getRuntime().exec(currentPath + name + " " + args.joinToString(" "))

        process.outputStream.write(input.toByteArray())
        process.waitFor()

        return CommandResult(process.inputStream.readBytes().toString(Charset.defaultCharset()))
    }

    private fun getCurrentPath(): Path {
        return Paths.get(getVariable(CURRENT_DIRECTORY_PATH))
    }
}