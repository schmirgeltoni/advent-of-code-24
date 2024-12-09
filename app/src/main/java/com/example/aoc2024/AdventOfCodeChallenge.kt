package com.example.aoc2024

interface AdventOfCodeChallenge {
    val name: String
        get() = this::class.simpleName.toString()

    /**
     * Run the code with the real input data and return the result
     */
    fun solution(): Any

    /**
     * Run the code with the test data from the example and return the result
     */
    fun test(): Any

    /**
     * Run both [solution] and [test]
     */
    fun runBoth() {
        println(
            """
                
            $name
            The result with test input is: ${this.test()}
            The result with real input is: ${this.solution()}
        """.trimIndent()
        )
    }
}