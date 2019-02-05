package ru.hse.spb.fedorov.cli.command

object ExitCommand : GeneralCommand() {
    override fun execute(args: List<String>, input: String): CommandResult {
        System.exit(0)
        return CommandResult("")
    }
}