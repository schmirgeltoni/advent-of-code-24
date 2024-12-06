package com.example.aoc2024.day4

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.readFileLines
import kotlin.math.abs

object Day4Part2 : AdventOfCodeChallenge {
    private fun findXMASWordSearch(input: List<String>): Int {
        var count = 0
        for (i in 1..<input.size - 1) {
            for (j in 1..<input[0].length - 1) {
                if (input[i][j] == 'A' && abs(input[i - 1][j - 1] - input[i + 1][j + 1]) == 6 && abs(input[i - 1][j + 1] - input[i + 1][j - 1]) == 6)
                    count++
            }
        }
        return count
    }


    override fun runWithRealData(): Int {
        return findXMASWordSearch(readFileLines("day4"))
    }

    override fun runWithExampleData(): Int {
        return findXMASWordSearch(Day4Part1.testData)
    }
}