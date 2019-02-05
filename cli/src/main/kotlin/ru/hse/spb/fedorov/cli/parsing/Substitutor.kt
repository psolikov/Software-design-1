package ru.hse.spb.fedorov.cli.parsing

interface Substitutor {
    companion object {
        const val VARIABLE_MARKER = '$'
    }

    fun substitute(input: String): String
}