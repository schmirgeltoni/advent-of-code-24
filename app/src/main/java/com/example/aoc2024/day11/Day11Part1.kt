package com.example.aoc2024.day11

import com.example.aoc2024.AdventOfCodeChallenge

object Day11Part1 : AdventOfCodeChallenge {

    val testData = listOf<Long>(125, 17)

    val input = listOf<Long>(5, 89749, 6061, 43, 867, 1965860, 0, 206250)

    fun Long.splitHalves(): List<Long> {
        val string = this.toString()
        return listOf(
            string.substring(0, string.length / 2).toLong(),
            string.substring(string.length / 2, string.length).toLong()
        )
    }

    fun List<Long>.blink(): List<Long> = map {
        if (it == 0L) listOf(1L)
        else if (it.toString().length % 2 == 0) it.splitHalves()
        else listOf(it * 2024L)
    }.flatten()


    override fun solution(): Any {
        var result = input
        repeat(25) {
            result = result.blink()
        }
        return result.size
    }

    override fun test(): Any {
        var result = testData
        repeat(25) {
            result = result.blink()
        }
        return result.size
    }
}