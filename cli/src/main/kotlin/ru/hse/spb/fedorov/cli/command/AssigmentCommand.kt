package ru.hse.spb.fedorov.cli.command

import ru.hse.spb.fedorov.cli.environment.Environment
import ru.hse.spb.fedorov.cli.exception.CommandShellException

object AssigmentCommand : EnvironmentalCommand() {
    override fun execute(args: List<String>, input: String, environment: Environment): CommandResult {
        if (args.size != 2) throw CommandShellException("Assigment with more that 2 arguments")

        environment.setVariable(args[0], args[1])
        return CommandResult("")
    }
}