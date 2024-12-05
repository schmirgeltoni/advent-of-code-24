package com.example.aoc2024.day5

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.readWholeFile

object Day5Part1 : AdventOfCodeChallenge {

    private val testOrdering =
        mapOf(
            47 to listOf(53, 13, 61, 29),
            97 to listOf(13, 61, 47, 29, 53, 75),
            75 to listOf(29, 53, 47, 61, 13),
            61 to listOf(13, 53, 29),
            29 to listOf(13),
            53 to listOf(29, 13)
        )

    private val testUpdates = listOf(
        listOf(75, 47, 61, 53, 29),
        listOf(97, 61, 53, 29, 13),
        listOf(75, 29, 13),
        listOf(75, 97, 47, 61, 53),
        listOf(61, 13, 29),
        listOf(97, 13, 75, 29, 47)
    )

    private fun getUpdates(input: String) =
        input.split("\r\n")
            .map { updatesString: String ->
                updatesString.split(",").map { it.toInt() }
            }

    private fun getMapOfPageOrderingRules(input: String): Map<Int, List<Int>> =
        input.split("\r\n").map {
            it.split("|")
        }.map {
            Pair(it[0].toInt(), it[1].toInt())
        }
            .groupBy({ it.first }, { it.second })

    private fun List<Int>.isUpdatesCorrectlyOrdered(orderingRules: Map<Int, List<Int>>): Int {
        for (i in 0..<size - 1) {
            if (!(orderingRules[this[i]]
                    ?: listOf()).contains(this[i + 1])
            ) return 0
        }
        return this[(size - 1) / 2]
    }

    /**
     * @return the page ordering rules in [Pair.first] and the updates in [Pair.second]
     * @throws IllegalArgumentException if the input could not be split up correctly
     */
    private fun splitPageOrderingRulesAndUpdates(input: String): Pair<String, String> {
        val split = input.split("\r\n\r\n")
            .also { require(it.size == 2) }
        return Pair(split[0], split[1])
    }

    override fun solution(): Any {
        val transformedData =
            splitPageOrderingRulesAndUpdates(readWholeFile("day5"))

        return getUpdates(transformedData.second).sumOf {
            it.isUpdatesCorrectlyOrdered(
                getMapOfPageOrderingRules(
                    transformedData.first
                )
            )
        }
    }

    override fun runWithExampleData(): Any {
        return testUpdates.sumOf {
            it.isUpdatesCorrectlyOrdered(
                testOrdering
            )
        }
    }
}