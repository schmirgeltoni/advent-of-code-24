package com.example.aoc2024.day12

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.Point
import com.example.aoc2024.above
import com.example.aoc2024.below
import com.example.aoc2024.leftOf
import com.example.aoc2024.readFileLines
import com.example.aoc2024.rightOf


object Day12Part1 : AdventOfCodeChallenge {

    @Suppress("SpellCheckingInspection")
    val testData = """
        RRRRIICCFF
        RRRRIICCCF
        VVRRRCCFFF
        VVRCCCJFFF
        VVVVCJJCFE
        VVIVCCJJEE
        VVIIICJJEE
        MIIIIIJJEE
        MIIISIJEEE
        MMMISSJEEE
    """.trimIndent().toGardenMap()

    data class GardenPlot(val plantType: Char, var accounted: Boolean = false) {
        override fun toString(): String =
            if (accounted) plantType.lowercase() else plantType.toString()

    }

    private fun String.toGardenMap(): List<List<GardenPlot>> =
        split("\n").map { it.toList().map { plantType -> GardenPlot(plantType) } }

    private fun List<List<GardenPlot>>.findPlotPrice(point: Point, plantType: Char): Int {
        var area = 0
        var perimeter = 0
        fun recursivePlotFind(p: Point) {
            println("Searching $plantType at $p")
            this[p.y][p.x].accounted = true
            area++
            val above = above(p.y, p.x)
            val below = below(p.y, p.x)
            val leftOf = leftOf(p.y, p.x)
            val rightOf = rightOf(p.y, p.x)
            if (above != null) {
                if (above == GardenPlot(plantType, false))
                    recursivePlotFind(Point(y = p.y - 1, x = p.x))
                else if (above.plantType != plantType)
                    perimeter++
            } else
                perimeter++
            if (below != null) {
                if (below == GardenPlot(plantType, false))
                    recursivePlotFind(Point(y = p.y + 1, x = p.x))
                else if (below.plantType != plantType)
                    perimeter++
            } else
                perimeter++
            if (leftOf != null) {
                if (leftOf == GardenPlot(plantType, false))
                    recursivePlotFind(Point(y = p.y, x = p.x - 1))
                else if (leftOf.plantType != plantType)
                    perimeter++
            } else
                perimeter++
            if (rightOf != null) {
                if (rightOf == GardenPlot(plantType, false))
                    recursivePlotFind(Point(y = p.y, x = p.x + 1))
                else if (rightOf.plantType != plantType)
                    perimeter++
            } else
                perimeter++
        }
        recursivePlotFind(point)
        return area /*.log("The area is: ")*/ * perimeter //.log("The perimeter is: ", "\n")
    }

    override fun solution(): Any {
        var sum = 0
        val input = readFileLines("day12").map { it.toList().map { plantType -> GardenPlot(plantType) } }
        input.indices.forEach { y ->
            input[0].indices.forEach { x ->
                if (!input[y][x].accounted) sum += input.findPlotPrice(
                    Point(y = y, x = x),
                    input[y][x].plantType
                )
            }
        }
        return sum
    }

    override fun test(): Any {
        var sum = 0
        testData.indices.forEach { y ->
            testData[0].indices.forEach { x ->
                if (!testData[y][x].accounted) sum += testData.findPlotPrice(
                    Point(y = y, x = x),
                    testData[y][x].plantType
                )
            }
        }
        return sum
    }
}