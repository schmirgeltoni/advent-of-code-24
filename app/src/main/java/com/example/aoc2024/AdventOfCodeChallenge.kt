package com.example.aoc2024

interface AdventOfCodeChallenge {
    val name: String
        get() = this.toString().substring(25..33).replace("(?<=[a-zA-Z])(?=\\d)".toRegex(), " ")
            .replace("(?<=\\d)(?=[a-zA-Z])".toRegex(), " ")

    fun solution(): Any
    fun runWithExampleData(): Any
    fun runBoth() {
        println(
            """
            $name
            The result with test input is: ${this.runWithExampleData()}
            The result with real input is: ${this.solution()}
            
        """.trimIndent()
        )
    }
}