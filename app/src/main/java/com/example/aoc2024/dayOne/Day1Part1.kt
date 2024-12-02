package com.example.aoc2024.dayOne

import java.io.File
import kotlin.math.abs

object Day1Part1 {

    fun findDistanceBetweenTwoLists(firstList: List<Int>, secondList: List<Int>): Int {
        if (firstList.size == secondList.size) {
            var totalDistance = 0

            firstList.sorted().zip(secondList.sorted()) { first, second ->
                totalDistance += abs(first - second)
            }

            return totalDistance
        }
        return -1
    }

    fun solution() : Int {
        val numbers =
            File("app\\src\\main\\java\\com\\example\\aoc2024\\dayOne\\Input.txt").readLines()
        val firstList: MutableList<Int> = mutableListOf()
        val secondList: MutableList<Int> = mutableListOf()
        numbers.forEach {
            val line = it.split("   ")
            firstList.add(line[0].toInt())
            secondList.add(line[1].toInt())
        }

        return findDistanceBetweenTwoLists(firstList, secondList)
    }
}
