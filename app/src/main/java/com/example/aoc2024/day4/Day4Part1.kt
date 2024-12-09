package com.example.aoc2024.day4

import com.example.aoc2024.AdventOfCodeChallenge
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

    private fun List<String>.extractDiagonals(minLength: Int): List<String> {
        val nRows = this.size
        val nCols = this[0].length
        val ret = mutableListOf<String>()

        // Extract top-left to bottom-right (\) diagonals
        for (start in 0 until nCols + nRows - 1) {
            val diagonal = StringBuilder()
            for (row in 0 until nRows) {
                val col = start - row
                if (col in 0 until nCols) {
                    diagonal.append(this[row][col])
                }
            }
            if (diagonal.length >= minLength) {
                ret.add(diagonal.toString())
            }
        }

        // Extract top-right to bottom-left (/) diagonals
        for (start in -(nRows - 1) until nCols) {
            val diagonal = StringBuilder()
            for (row in 0 until nRows) {
                val col = start + row
                if (col in 0 until nCols) {
                    diagonal.append(this[row][col])
                }
            }
            if (diagonal.length >= minLength) {
                ret.add(diagonal.toString())
            }
        }

        return ret
    }

    private fun String.searchStringAndReturnOccurrences(regex: Regex): Int {
        return regex.findAll(this).count() + regex.findAll(this.reversed()).count()
    }

    override fun solution(): Int {
        return findXMASwordSearch(readFileLines("day4"))
    }

    override fun test(): Int {
        return findXMASwordSearch(testData)
    }
}