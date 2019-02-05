package ru.hse.spb.fedorov.cli.parsing

class StandardQuoteHandler : QuoteHandler {
    private val weakQuotes = setOf('"')
    private val strongQuotes = setOf('\'')
    private var topQuote: Char? = null

    private fun isQuote(c: Char) = c in weakQuotes || c in strongQuotes

    override fun canSubstitute(): Boolean = if (topQuote == null) true else topQuote!! in weakQuotes

    override fun addSymbol(c: Char) {
        if (inQuotes()) {
            if (topQuote == c)
                topQuote = null
        } else {
            if (isQuote(c))
                topQuote = c
        }
    }

    override fun inQuotes(): Boolean = topQuote != null
}