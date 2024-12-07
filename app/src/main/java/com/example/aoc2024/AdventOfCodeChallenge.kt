package com.example.aoc2024

interface AdventOfCodeChallenge {
    val name: String
        get() = this::class.simpleName.toString()

    fun runWithRealData(): Any
    fun runWithExampleData(): Any
    fun runBoth() {
        println(
            """
            $name
            The result with test input is: ${this.runWithExampleData()}
            The result with real input is: ${this.runWithRealData()}
            
        """.trimIndent()
        )
    }
}