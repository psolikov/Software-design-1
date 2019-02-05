package ru.hse.spb.fedorov.cli.parsing

/**
 * An abstraction of a quote handler for the command shell
 */
interface QuoteHandler {
    fun inQuotes(): Boolean
    fun addSymbol(c: Char)
    fun canSubstitute(): Boolean
}