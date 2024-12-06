package com.example.aoc2024.day4

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.extractDiagonals
import com.example.aoc2024.getColumnsAsStrings
import com.example.aoc2024.readFileLines

object Day4Part1 : AdventOfCodeChallenge {

    @Suppress("SpellCheckingInspection")
    val testData = listOf(
        "MMMSXXMASM",
        "MSAMXMSMSA",
        "AMXSXMAAMM",
        "MSAMASMSMX",
        "XMASAMXAMM",
        "XXAMMXXAMA",
        "SMSMSASXSS",
        "SAXAMASAAA",
        "MAMMMXMMMM",
        "MXMXAXMASX"
    )

    private val xmasRegex = Regex.fromLiteral("XMAS")

    private fun findXMASwordSearch(input: List<String>): Int {
        var count = 0
        // horizontally, backwards and forwards
        input.forEach { string ->
            string.searchStringAndReturnOccurrences(xmasRegex).also { count += it }
        }
        // vertically, backwards and forwards
        input.getColumnsAsStrings().forEach { string ->
            string.searchStringAndReturnOccurrences(xmasRegex).also { count += it }
        }
        // diagonal
        input.extractDiagonals(4).forEach { string ->
            string.searchStringAndReturnOccurrences(xmasRegex).also { count += it }
        }
        return count
    }

    private fun String.searchStringAndReturnOccurrences(regex: Regex): Int {
        return regex.findAll(this).count() + regex.findAll(this.reversed()).count()
    }

    override fun runWithRealData(): Int {
        return findXMASwordSearch(readFileLines("day4"))
    }

    override fun runWithExampleData(): Int {
        return findXMASwordSearch(testData)
    }
}