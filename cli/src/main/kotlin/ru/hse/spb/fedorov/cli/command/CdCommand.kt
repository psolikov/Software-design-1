package ru.hse.spb.fedorov.cli.command

import ru.hse.spb.fedorov.cli.environment.Environment
import java.nio.file.Paths

object CdCommand : EnvironmentalCommand() {
    override fun execute(args: List<String>, input: String, environment: Environment): CommandResult {
        println(environment.getVariable(Environment.CURRENT_DIRECTORY_PATH))
        if (args.isEmpty()) {
            println("homedir : " + System.getProperty("user.home"))
            environment.setVariable(Environment.CURRENT_DIRECTORY_PATH, System.getProperty("user.home"))
            return CommandResult("")
        }

        if (args.size >= 2) {
            return CommandResult("Too many arguments")
        }

        if (!args[0].startsWith("/") && Paths.get(environment.getVariable(Environment.CURRENT_DIRECTORY_PATH) + "/" + args[0]).toFile().isDirectory) {
            environment.setVariable(
                Environment.CURRENT_DIRECTORY_PATH,
                environment.getVariable(Environment.CURRENT_DIRECTORY_PATH) + "/" + args[0]
            )
            return CommandResult("")
        } else if (Paths.get(args[0]).toFile().isDirectory) {
            environment.setVariable(Environment.CURRENT_DIRECTORY_PATH, args[0])
            return CommandResult("")
        } else if (Paths.get(args[0]).toFile().isFile) {
            return CommandResult("cd: ${args[0]} is a file")
        } else {
            return CommandResult("cd: ${args[0]}: No such file or directory")
        }
    }
}