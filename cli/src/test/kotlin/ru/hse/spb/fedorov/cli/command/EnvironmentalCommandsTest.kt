package ru.hse.spb.fedorov.cli.command

import org.junit.Test
import org.junit.Assert.*
import ru.hse.spb.fedorov.cli.environment.Environment
import ru.hse.spb.fedorov.cli.environment.MapEnvironment
import ru.hse.spb.fedorov.cli.environment.StandardEnvironmentFactory
import ru.hse.spb.fedorov.cli.exception.CommandShellException
import java.nio.file.Paths

class EnvironmentalCommandsTest {
    @Test
    fun testAssignmentCommand() {
        val environment = MapEnvironment()
        assertEquals("", AssigmentCommand.execute(listOf("like", "cats and dogs"), "", environment).output)
        assertEquals("cats and dogs", environment.getVariable("like"))
    }

    @Test(expected = CommandShellException::class)
    fun testAssignmentCommandShellExceptionTooManyArguments() {
        val environment = MapEnvironment()
        assertEquals("", AssigmentCommand.execute(listOf("like", "cats and dogs", "and boys"), "", environment).output)
    }

    @Test(expected = CommandShellException::class)
    fun testAssignmentCommandShellExceptionTooLittleArguments() {
        val environment = MapEnvironment()
        assertEquals("", AssigmentCommand.execute(listOf("like"), "", environment).output)
    }

    @Test
    fun testPwdCommand() {
        val environment = StandardEnvironmentFactory.createEnvironment()
        val pwd = PwdCommand.execute(listOf(), "", environment).output
        print(pwd)
        assertEquals(Paths.get(".").toAbsolutePath(), Paths.get(pwd + "/.").toAbsolutePath())
    }

    @Test
    fun testLsCommand() {
        val environment = StandardEnvironmentFactory.createEnvironment()
        val ls = LsCommand.execute(listOf(Paths.get(".").toAbsolutePath().toString()), "", environment).output
        println(Paths.get(".").toAbsolutePath())
        println(ls)
        assertEquals(
            ls,
            "out  gradle  gradlew  build.gradle  .gradle  gradle.properties  gradlew.bat  settings.gradle  .idea  src  "
        )

    }

    @Test
    fun testCdCommand() {
        val environment = StandardEnvironmentFactory.createEnvironment()
        val cd = CdCommand.execute(listOf("/"), "", environment).output
        assertEquals("/", environment.getVariable(Environment.CURRENT_DIRECTORY_PATH))
    }
}