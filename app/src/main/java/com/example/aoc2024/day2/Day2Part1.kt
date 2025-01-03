package com.example.aoc2024.day2

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.readFileLines
import com.example.aoc2024.toIntList
import kotlin.math.abs

object Day2Part1 : AdventOfCodeChallenge {

    val testReports = listOf(
        listOf(7, 6, 4, 2, 1),
        listOf(1, 2, 7, 8, 9),
        listOf(9, 7, 6, 2, 1),
        listOf(1, 3, 2, 4, 5),
        listOf(8, 6, 4, 4, 1),
        listOf(1, 3, 6, 7, 9)
    )

    fun List<Int>.isReportSafe(): Boolean {
        if (this == emptyList<Int>()) return false
        val firstPair = Pair(this[0], this[1])
        return if (!firstPair.differenceIsSafe()) {
            false
        } else if (firstPair.isDescending()) {
            this.checkRestOfList { it.first < it.second }
        } else if (firstPair.isAscending()) {
            this.checkRestOfList { it.first > it.second }
        } else {
            true
        }
    }

    private fun Pair<Int, Int>.differenceIsSafe(): Boolean = abs(this.first - this.second) in 1..3
    private fun Pair<Int, Int>.isAscending(): Boolean = this.first < this.second
    private fun Pair<Int, Int>.isDescending(): Boolean = this.first > this.second

    /**
     * @return: false if the condition applies to any two sequential numbers in the list
     */
    private fun List<Int>.checkRestOfList(condition: (Pair<Int, Int>) -> Boolean): Boolean {
        for (i in 1..this.size - 2) {
            val pair = Pair(this[i], this[i + 1])
            if (condition(pair)) {
                return false
            } else if (!pair.differenceIsSafe()) {
                return false
            }
        }
        return true
    }

    override fun solution(): Int {
        return readFileLines("day2").count {
            it.split(" ").toIntList().isReportSafe()
        }
    }

    override fun test(): Int {
        return testReports.count {
            it.isReportSafe()
        }
    }
}