package com.example.aoc2024

import com.example.aoc2024.day1.Day1Part1
import com.example.aoc2024.day1.Day1Part2
import com.example.aoc2024.day2.Day2Part1
import com.example.aoc2024.day2.Day2Part2
import com.example.aoc2024.day3.Day3Part1
import com.example.aoc2024.day3.Day3Part2

val Challenges = listOf(
    Day1Part1,
    Day1Part2,
    Day2Part1,
    Day2Part2,
    Day3Part1,
    Day3Part2,
)

fun main() {
    Challenges.forEach {
        it.runBoth()
    }
}

interface AdventOfCodeChallenge {
    val name : String

    fun solution(): Any

    fun runWithExampleData(): Any

    fun runBoth() {
        println("""
            $name
            The result with test data is: ${this.runWithExampleData()}
            The result with real data is: ${this.solution()}
            
        """.trimIndent())
    }
}
