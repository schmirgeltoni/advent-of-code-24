package com.example.aoc2024.day4

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.extractDiagonals
import com.example.aoc2024.getColumnsAsStrings
import com.example.aoc2024.readFileLines
import com.example.aoc2024.toListOfListOfChars

object Day4Part1 : AdventOfCodeChallenge {

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

    val xmasRegex = Regex.fromLiteral("XMAS")

    private fun findXMASwordSearch(input: List<String>): Int {
        var count = 0
        // horizontally, backwards and forwards
        input.forEach {
            it.searchStringAndReturnOccurrences(xmasRegex).also { count += it }
        }
        // vertically, backwards and forwards
        input.toListOfListOfChars().getColumnsAsStrings().forEach {
            it.searchStringAndReturnOccurrences(xmasRegex).also { count += it }
        }
        // diagonal
        input.extractDiagonals(4).forEach {
            it.searchStringAndReturnOccurrences(xmasRegex).also { count += it }
        }
        return count
    }

    private fun String.searchStringAndReturnOccurrences(regex: Regex): Int {
        return regex.findAll(this).count() + regex.findAll(this.reversed()).count()
    }

    override fun solution(): Int {
        return findXMASwordSearch(readFileLines("day4"))
    }

    override fun runWithExampleData(): Int {
        return findXMASwordSearch(testData)
    }
}