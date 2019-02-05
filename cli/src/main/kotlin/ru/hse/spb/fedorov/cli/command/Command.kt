package ru.hse.spb.fedorov.cli.command

import ru.hse.spb.fedorov.cli.environment.Environment

data class CommandResult(val output: String)

sealed class Command

abstract class EnvironmentalCommand : Command() {
    abstract fun execute(args: List<String>, input: String, environment: Environment): CommandResult
}

abstract class GeneralCommand : Command() {
    abstract fun execute(args: List<String>, input: String): CommandResult
}

fun Command.executeWithEnvironment(args: List<String>, input: String, environment: Environment): CommandResult = when (this) {
        is EnvironmentalCommand -> execute(args, input, environment)
        is GeneralCommand -> execute(args, input)
    }
