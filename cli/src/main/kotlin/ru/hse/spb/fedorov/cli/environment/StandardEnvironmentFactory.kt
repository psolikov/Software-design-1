package ru.hse.spb.fedorov.cli.environment

import ru.hse.spb.fedorov.cli.command.*
import java.nio.file.Paths

/**
 * Factory for the creation of environment with all standard operations.
 */
object StandardEnvironmentFactory : EnvironmentFactory {
    override fun createEnvironment(): Environment {
        val environment = MapEnvironment()

        environment.setCommand("=", AssigmentCommand)
        environment.setCommand("echo", EchoCommand)
        environment.setCommand("cat", CatCommand)
        environment.setCommand("exit", ExitCommand)
        environment.setCommand("pwd", PwdCommand)
        environment.setCommand("wc", WcCommand)
        environment.setCommand("grep", GrepCommand)
        environment.setCommand("ls", LsCommand)
        environment.setCommand("cd", CdCommand)

        environment.setVariable(Environment.CURRENT_DIRECTORY_PATH, Paths.get("").toAbsolutePath().toString())

        return environment
    }
}