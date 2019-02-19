package ru.hse.spb.fedorov.cli.command

import ru.hse.spb.fedorov.cli.environment.Environment
import java.nio.file.Paths

object LsCommand : EnvironmentalCommand() {
    /**
     * Returns filenames from provided directories
     */
    override fun execute(args: List<String>, input: String, environment: Environment): CommandResult {
        if (args.isEmpty()) return CommandResult(listDirectory(environment.getVariable(Environment.CURRENT_DIRECTORY_PATH)))

        println(input)
        if (args.size == 1) return CommandResult(listDirectory(args[0]))

        return CommandResult(listManyDirectories(args))
    }

    private fun getErrorMessage(path: String): String {
        return "ls: $path: No such file or directory"
    }

    private fun listDirectory(path: String): String {
        val directory = Paths.get(path).toFile()
        if (!directory.exists()) return getErrorMessage(path)
        if (directory.isFile) return directory.name
        var output = ""
        for (file in directory.list()) {
            output += file
            output += "  "
        }
        return output
    }

    private fun listManyDirectories(args: List<String>): String {
        var output = ""
        for (arg in args) {
            val argOutput = listDirectory(arg)
            if (argOutput != getErrorMessage(arg)) output += "$arg:\n"
            output += argOutput
            output += "\n\n"
        }
        return output
    }
}