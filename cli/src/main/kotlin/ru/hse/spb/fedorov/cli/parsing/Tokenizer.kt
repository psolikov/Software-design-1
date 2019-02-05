package ru.hse.spb.fedorov.cli.parsing

interface Tokenizer {
    fun tokenize(input: String): List<String>
}