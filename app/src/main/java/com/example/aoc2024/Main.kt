package com.example.aoc2024

import com.example.aoc2024.day3.Day3Part2

fun main() {
    Day3Part2.runBoth()
}

interface AdventOfCodeChallenge {
    fun solution(): Any

    fun runWithExampleData(): Any

    fun runBoth() {
        println("The result with test data is: ${this.runWithExampleData()}")
        println("The result with real data is: ${this.solution()}")
    }
}
