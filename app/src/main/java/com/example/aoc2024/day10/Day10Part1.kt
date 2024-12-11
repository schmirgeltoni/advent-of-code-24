package com.example.aoc2024.day10

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.Point
import com.example.aoc2024.logMatrix
import com.example.aoc2024.readFileLines

object Day10Part1 : AdventOfCodeChallenge {

    val testData = """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
    """.trimIndent().split("\n").map { it.toList().map { char -> char.digitToInt() } }

    val testTrail = """
        ..90..9
        ...1.98
        ...2..7
        6543456
        765.987
        876....
        987....
    """.trimIndent().trimIndent().split("\n").map { it.toList().map { char -> if (char == '.') -1 else char.digitToInt() } }

    private fun List<List<Int>>.findHikingScore(y: Int, x: Int): MutableSet<Point> {
        val set = mutableSetOf<Point>()
        fun findHikingDestination(y: Int, x: Int) {
            val currentHeight = this[y][x]
            if (currentHeight == 9) {
                set.add(Point(y = y, x = x))
            }
            val nextHeight = currentHeight + 1
            //up
            if (y != 0 && this[y - 1][x] == nextHeight) findHikingDestination(y - 1, x)
            // down
            if (y != size - 1 && this[y + 1][x] == nextHeight) findHikingDestination(y + 1, x)
            // left
            if (x != 0 && this[y][x - 1] == nextHeight) findHikingDestination(y, x - 1)
            // right
            if (x != this[0].size - 1 && this[y][x + 1] == nextHeight) findHikingDestination(y, x + 1)
        }

        findHikingDestination(y, x)
        return set
    }

    private fun List<List<Int>>.sumUpTrailScores(): Int {
        var sum = 0
        for (i in this.indices) {
            for (j in this[0].indices) {
                if (this[i][j] == 0) {
                    val trailScore = this.findHikingScore(i, j)
                    if (trailScore.isNotEmpty())
                        sum += trailScore.size
                }
            }
        }
        return sum
    }

    override fun solution(): Any {
        return readFileLines("day10").map { it.toList().map { it.digitToInt() } }.sumUpTrailScores()
    }

    override fun test(): Any {
        testTrail.logMatrix(mapOf(-1 to "."))
        return testData.sumUpTrailScores()
    }
}