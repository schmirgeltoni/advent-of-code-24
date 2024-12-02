package com.example.aoc2024

import com.example.aoc2024.day1.Day1Part1
import com.example.aoc2024.day1.Day1Part2

fun main() {
    println(Day1Part1.solution())
    println(Day1Part2.solution())
}

fun interface AoCSolution {
    fun solution() : Any
}
