package com.example.aoc2024.day2

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.readFile
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
            println(
                """
                    $this: unsafe
                    Reason: Sequence ${firstPair.first} and ${firstPair.second} do not match distance requirement
                """.trimIndent()
            )
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
                println(
                    """
                    $this: unsafe
                    Reason: Sequence ${this[i]} and ${this[i + 1]} violated order
                """.trimIndent()
                )
                return false

            } else if (!pair.differenceIsSafe()) {
                println(
                    """
                    $this: unsafe
                    Reason: Sequence ${this[i]} and ${this[i + 1]} do not match distance requirement
                """.trimIndent()
                )
                return false
            }
        }
        println("$this: safe")
        return true
    }

    override fun solution(): Int {
        return readFile("app\\src\\main\\java\\com\\example\\aoc2024\\day2\\Input.txt").count {
            it.split(" ").toIntList().isReportSafe()
        }
    }

    override fun runWithExampleData(): Int {
        return testReports.count {
            it.isReportSafe()
        }
    }
}