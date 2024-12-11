package com.example.aoc2024.day10

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.day10.Day10Part1.testData
import com.example.aoc2024.readFileLines

object Day10Part2: AdventOfCodeChallenge {

    private fun List<List<Int>>.getHikingTrailRatings(y: Int, x: Int): Int {
        val currentHeight = this[y][x]
        if (currentHeight == 9) return 1
        var sum = 0
        //up
        if (y != 0 && this[y - 1][x] == currentHeight + 1) sum += getHikingTrailRatings(y - 1, x)
        // down
        if (y != size - 1 && this[y + 1][x] == currentHeight + 1) sum += getHikingTrailRatings(y + 1, x)
        // left
        if (x != 0 && this[y][x - 1] == currentHeight + 1) sum += getHikingTrailRatings(y, x - 1)
        // right
        if (x != this[0].size - 1 && this[y][x + 1] == currentHeight + 1) sum += getHikingTrailRatings(y, x + 1)
        return sum
    }

    private fun List<List<Int>>.sumUpTrailRatings() : Int {
        return this.indices.sumOf { y ->
            this[0].indices.sumOf { x ->
                if (this[y][x] == 0)
                    getHikingTrailRatings(y,x)
                else
                    0
            }
        }
    }

    override fun solution(): Any {
        return readFileLines("day10").map { it.toList().map { it.digitToInt() } }.sumUpTrailRatings()
    }

    override fun test(): Any {
        return testData.sumUpTrailRatings()
    }
}