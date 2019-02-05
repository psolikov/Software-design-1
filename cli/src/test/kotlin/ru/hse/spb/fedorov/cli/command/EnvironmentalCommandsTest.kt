package ru.hse.spb.fedorov.cli.command

import org.junit.Test
import org.junit.Assert.*
import ru.hse.spb.fedorov.cli.environment.MapEnvironment
import ru.hse.spb.fedorov.cli.environment.StandardEnvironmentFactory
import java.nio.file.Paths

class EnvironmentalCommandsTest {
    @Test
    fun testAssignmentCommand() {
        val environment = MapEnvironment()
        assertEquals("", AssigmentCommand.execute(listOf("like", "cats and dogs"), "", environment).output)
        assertEquals("cats and dogs", environment.getVariable("like"))
    }

    @Test
    fun testPwdCommand() {
        val environment = StandardEnvironmentFactory.createEnvironment()
        val pwd = PwdCommand.execute(listOf(), "", environment).output
        assertEquals(Paths.get(".").toAbsolutePath(), Paths.get(pwd + "/.").toAbsolutePath())
    }
}