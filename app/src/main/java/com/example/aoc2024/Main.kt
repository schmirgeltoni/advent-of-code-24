package com.example.aoc2024

import com.example.aoc2024.day2.Day2Part1
import com.example.aoc2024.day2.Day2Part2

fun main() {
    Day2Part2.runBoth()
}

interface AdventOfCodeChallenge {
    fun solution(): Any

    fun runWithExampleData(): Any

    fun runBoth() {
        println("The result with example data is: ${this.runWithExampleData()}")
        println("The result with real data is: ${this.solution()}")
    }
}
