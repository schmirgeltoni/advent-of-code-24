package com.example.aoc2024.day11

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.day11.Day11Part1.blink
import com.example.aoc2024.day11.Day11Part1.input
import com.example.aoc2024.day11.Day11Part1.testData

object Day11Part2 : AdventOfCodeChallenge {

    override fun solution(): Any {
        var result = input
        repeat(75) {
            result = result.blink()
            println("Solution | Step $it, list size: ${result.size}")
        }
        return result.size
    }

    override fun test(): Any {
        var result = testData
        repeat(75) {
            result = result.blink()
            println("Test | Step $it, list size: ${result.size}")
        }
        return result.size
    }
}