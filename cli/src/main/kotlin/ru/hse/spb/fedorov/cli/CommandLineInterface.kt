package ru.hse.spb.fedorov.cli

import ru.hse.spb.fedorov.cli.environment.Environment
import ru.hse.spb.fedorov.cli.exception.CommandShellException
import ru.hse.spb.fedorov.cli.exception.ParserException
import ru.hse.spb.fedorov.cli.executor.StandardCommandExecutor
import ru.hse.spb.fedorov.cli.parsing.StandardParserFactory
import java.io.IOException
import java.nio.file.InvalidPathException

class CommandLineInterface(val environment: Environment) {
    val parser = StandardParserFactory.createParser(environment)
    val executor = StandardCommandExecutor(environment)

    fun run() {
        while (true) {
            val command = readLine() ?: break

            try {
                val commands = parser.parse(command)
                print(executor.execute(commands))
            } catch (e: InvalidPathException) {
                print(e.message)
            } catch (e: ParserException) {
                print(e.message)
            } catch (e: CommandShellException) {
                print(e.message)
            } catch (e: IOException) {
                print(e.message)
            }
            print("\n")
        }
    }
}