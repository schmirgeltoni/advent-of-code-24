package com.example.aoc2024.day1

import com.example.aoc2024.AoCSolution

object Day1Part2 : AoCSolution {

    private fun calculateSimilarityScore(firstList: List<Int>, secondList: List<Int>): Int {
        val map = getMapOfList(secondList)
        var score = 0

        firstList.forEach {
            val amountInMap = map[it] ?: 0
            score += it * amountInMap
        }

        return score
    }

    private fun <T> getMapOfList(list: List<T>): Map<T, Int> = list.groupingBy { it }.eachCount()

    override fun solution(): Int {
        val lists = Day1Part1.getListsFromFile()

        return calculateSimilarityScore(
            lists.first,
            lists.second
        )
    }
}