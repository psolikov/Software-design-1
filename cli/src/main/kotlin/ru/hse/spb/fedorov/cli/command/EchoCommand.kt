package ru.hse.spb.fedorov.cli.command

object EchoCommand : GeneralCommand() {
    override fun execute(args: List<String>, input: String): CommandResult {
        return CommandResult(args.joinToString(" "))
    }
}