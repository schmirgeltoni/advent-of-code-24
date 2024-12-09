package com.example.aoc2024.day1

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.countOccurrences

object Day1Part2 : AdventOfCodeChallenge {

    private fun calculateSimilarityScore(firstList: List<Int>, secondList: List<Int>): Int {
        return firstList.sumOf { it * (countOccurrences(secondList)[it] ?: 0) }
    }

    override fun solution(): Int {
        val lists = Day1Part1.getListsFromInput()

        return calculateSimilarityScore(
            lists.first,
            lists.second
        )
    }

    override fun test(): Int {
        return calculateSimilarityScore(
            Day1Part1.exampleListOne,
            Day1Part1.exampleListTwo
        )
    }
}