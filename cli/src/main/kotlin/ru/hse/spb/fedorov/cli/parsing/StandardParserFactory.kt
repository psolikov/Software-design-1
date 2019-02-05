package ru.hse.spb.fedorov.cli.parsing

import ru.hse.spb.fedorov.cli.environment.Environment

object StandardParserFactory {
    fun createParser(environment: Environment) = GeneralParser(StandardSubstitutor(environment), StandardTokenizer)
}