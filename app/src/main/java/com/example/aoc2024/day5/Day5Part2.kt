package com.example.aoc2024.day5

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.day5.Day5Part1.getMapOfPageOrderingRules
import com.example.aoc2024.day5.Day5Part1.getUpdates
import com.example.aoc2024.day5.Day5Part1.splitPageOrderingRulesAndUpdates
import com.example.aoc2024.day5.Day5Part1.testOrdering
import com.example.aoc2024.day5.Day5Part1.testUpdates
import com.example.aoc2024.readWholeFile

object Day5Part2 : AdventOfCodeChallenge {

    private fun List<Int>.correctOrderAndReturnMiddleElement(orderingRules: Map<Int, List<Int>>): Int {
        for (i in 0..<size - 1) {
            if (!(orderingRules[this[i]] ?: listOf()).contains(this[i + 1])) {
                for (j in indices) {
                    if (orderingRules[this[j]]?.intersect(this.toSet())?.size == size / 2) {
                        return this[j]
                    }
                }
            }
        }
        return 0
    }

    override fun runWithRealData(): Any {
        val transformedData =
            splitPageOrderingRulesAndUpdates(readWholeFile("day5"))

        val orderingRules = getMapOfPageOrderingRules(transformedData.first)

        return getUpdates(transformedData.second).sumOf {
            it.correctOrderAndReturnMiddleElement(
                orderingRules
            )
        }
    }

    override fun runWithExampleData(): Int {
        return testUpdates.sumOf {
            it.correctOrderAndReturnMiddleElement(testOrdering)
        }
    }
}