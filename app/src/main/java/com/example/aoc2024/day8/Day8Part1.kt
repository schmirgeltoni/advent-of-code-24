package com.example.aoc2024.day8

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.logMatrix
import com.example.aoc2024.readFileLines
import kotlin.math.abs

object Day8Part1 : AdventOfCodeChallenge {

    val testData = """
        ............
        ........0...
        .....0......
        .......0....
        ....0.......
        ......A.....
        ............
        ............
        ........A...
        .........A..
        ............
        ............
    """.trimIndent().split("\n").map {
        it.toList()
    }

    val testDataSolution = """
        ......#....#
        ...#....0...
        ....#0....#.
        ..#....0....
        ....0....#..
        .#....A.....
        ...#........
        #......#....
        ........A...
        .........A..
        ..........#.
        ..........#.
    """.trimIndent().split("\n").map {
        it.toList()
    }

    private fun getAntiNodes(map: List<Char>): Int {

        val uniqueStations = map.toMutableSet().also {
            it.remove('.')
        }
        val antiNodePositions = mutableSetOf<Int>()
        println("Stations: $uniqueStations")
        // for each Character find all AntiNodes
        for (i in map.indices) {
            // if character is found
            if (map[i] != '.') {
                val station = map[i]
                // go through the rest of the list
                for (j in i + 1..<map.size) {
                    // if character is found, calculate distance and add it to AntiNode positions
                    // if it's still in the map
                    if (map[j] == station) {
                        val distanceBetweenAntennae = j - i
                        antiNodePositions.add(i - distanceBetweenAntennae)
                        antiNodePositions.add(j + distanceBetweenAntennae)
                    }
                }
            }
        }
        val antiNodePositionsWithinBounds = antiNodePositions.filter { it in map.indices }
        /*val solution = map.toMutableList()
        antiNodePositionsWithinBounds.forEach { solution[it] = '#' }
        println("1 Lösung: $testDataSolution")
        println("2 Lösung: $solution")*/
        return antiNodePositionsWithinBounds.size
    }

    private fun getAntiNodes2D(map: List<List<Char>>): Int {
        val antinodePositions = mutableSetOf<Pair<Int, Int>>()
        val antinodeMap = map.map { it.toMutableList() }.toMutableList()
        for (y in map.indices) {
            for (x in map[0].indices) {
                // start new loop to find other antenna
                if (map[y][x] != '.') {
                    antinodePositions += map.findAntinodes(y, x, map[y][x])
                }
            }
        }
        antinodePositions.forEach {
            antinodeMap[it.first][it.second] = '#'
        }
        antinodeMap.logMatrix()
        return antinodePositions.size
    }

    private fun List<List<Char>>.findAntinodes(
        startY: Int,
        startX: Int,
        antenna: Char
    ): MutableSet<Pair<Int, Int>> {
        val antinodes = mutableSetOf<Pair<Int, Int>>()
        for (y in (if (startX == this[0].size - 1) startY + 1 else startY)..<this.size) {
            for (x in (if (startX == this[0].size - 1) 0 + 1 else startX)..<this[0].size) {
                if (this[y][x] == antenna) {
                    val dX = abs(y - startY)
                    val dY = abs(x - startX)
                    val antinode1X = startX - dX
                    val antinode1Y = startX - dY
                    val antinode2X = x + dX
                    val antinode2Y = y + dY
                    val antinode1 = Pair(antinode1Y , antinode1X)
                    val antinode2 = Pair(antinode2Y, antinode2X)
                    if (antinode1Y in this.indices && antinode1X in this[0].indices) {
                        antinodes.add(antinode1)
                    }
                    if (antinode2Y in this.indices && antinode2X in this[0].indices) {
                        antinodes.add(antinode2)
                    }
                }
            }
        }
        return antinodes
    }

    override fun solution(): Any {
        return 271
    }


    override fun test(): Any {
        return 14
    }
}