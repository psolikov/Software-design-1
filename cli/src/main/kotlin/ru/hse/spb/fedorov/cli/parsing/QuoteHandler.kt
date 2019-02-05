package ru.hse.spb.fedorov.cli.parsing

interface QuoteHandler {
    fun inQuotes(): Boolean
    fun addSymbol(c: Char)
    fun canSubstitute(): Boolean
}