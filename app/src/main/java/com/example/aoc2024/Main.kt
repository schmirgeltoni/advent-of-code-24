package com.example.aoc2024

import com.example.aoc2024.day2.Day2Part1

fun main() {
    //println("The result with example data is: ${Day2Part1.runWithExampleData()}")
    println("The result with real data is: ${Day2Part1.solution()}")
}

interface AdventOfCodeChallenge {
    fun solution(): Any

    fun runWithExampleData(): Any
}
