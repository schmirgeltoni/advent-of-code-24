package com.example.aoc2024.day11

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.countOccurrences
import com.example.aoc2024.day11.Day11Part1.blink
import com.example.aoc2024.day11.Day11Part1.input
import com.example.aoc2024.day11.Day11Part1.splitHalves
import com.example.aoc2024.day11.Day11Part1.testData

object Day11Part2 : AdventOfCodeChallenge {

    private fun Long.blink(): List<Long> =
        if (this == 0L) listOf(1L)
        else if (this.toString().length % 2 == 0) this.splitHalves()
        else listOf(this * 2024L)

    private fun List<Long>.betterBlink(n : Int): Long {
        // copy list
        var list = this

        repeat(20) {
            list = list.blink()
        }

        // make a map
        var map = countOccurrences(list).map {
            it.key to it.value.toLong()
        }.toMap().toMutableMap()

        repeat(n - 20) {
            val tempMap = mutableMapOf<Long, Long>()
            // do a blink for each entry and add it to the temp map
            map.forEach { entry ->
                entry.key.blink().forEach {
                    tempMap[it] = (tempMap[it] ?: 0) + entry.value
                }
            }
            map = tempMap
        }
        var sum = 0L
        map.forEach {
            sum += it.value
        }
        return sum
    }

    override fun solution(): Any {
        return input.betterBlink(75)
    }


    override fun test(): Any {
        return testData.betterBlink(75)
    }
}