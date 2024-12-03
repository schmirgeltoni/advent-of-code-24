package com.example.aoc2024

import com.example.aoc2024.day2.Day2Part1
import com.example.aoc2024.day2.Day2Part2
import com.example.aoc2024.day3.Day3Part1

fun main() {
    Day3Part1.runBoth()
}

interface AdventOfCodeChallenge {
    fun solution(): Any

    fun runWithExampleData(): Any

    fun runBoth() {
        println("The result with example data is: ${this.runWithExampleData()}")
        println("The result with real data is: ${this.solution()}")
    }
}
