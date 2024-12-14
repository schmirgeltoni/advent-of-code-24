package com.example.aoc2024.day12

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.Direction
import com.example.aoc2024.Point
import com.example.aoc2024.above
import com.example.aoc2024.below
import com.example.aoc2024.day12.Day12Part1.GardenPlot
import com.example.aoc2024.leftOf
import com.example.aoc2024.rightOf

object Day12Part2 : AdventOfCodeChallenge {

    class Garden {
        data class GardenPlot(
            val plantType: Char,
            var accountedFor: Boolean,
            var up: Boolean,
            var down: Boolean,
            var left: Boolean,
            var right: Boolean
        )
    }

    fun List<List<GardenPlot>>.findPlotPrice(point: Point, plantType: Char): Int {
        var area = 0
        var perimeter = 0
        var lastDirection: Direction? = null
        fun recursivePlotFind(p: Point) {
            println("Searching $plantType at $p")
            this[p.y][p.x].accounted = true
            area++
            val above = above(p.y, p.x)
            val below = below(p.y, p.x)
            val leftOf = leftOf(p.y, p.x)
            val rightOf = rightOf(p.y, p.x)
            if (above != null) {
                if (above == GardenPlot(plantType, false)) {
                    lastDirection = Direction.Up
                    recursivePlotFind(Point(y = p.y - 1, x = p.x))
                }
            }
            if (below != null) {
                if (below == GardenPlot(plantType, false)) {
                    lastDirection = Direction.Down
                    recursivePlotFind(Point(y = p.y + 1, x = p.x))
                }
            }
            if (leftOf != null) {
                if (leftOf == GardenPlot(plantType, false)) {
                    lastDirection = Direction.Left
                    recursivePlotFind(Point(y = p.y, x = p.x - 1))
                }
            }
            if (rightOf != null) {
                if (rightOf == GardenPlot(plantType, false)) {
                    lastDirection = Direction.Right
                    recursivePlotFind(Point(y = p.y, x = p.x + 1))
                }
            }
            recursivePlotFind(point)
        }
        return area /*.log("The area is: ")*/ * perimeter //.log("The perimeter is: ", "\n")
    }

    override fun solution(): Any {
        return -1
    }

    override fun test(): Any {
        return -1
    }
}