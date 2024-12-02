package com.example.aoc2024.day1

import com.example.aoc2024.AoCSolution
import java.io.File
import kotlin.math.abs

object Day1Part1 : AoCSolution {

    val dayOneExampleListOne = listOf(3,4,2,1,3,3)
    val dayOneExampleListTwo = listOf(4,3,5,3,9,3)

    private fun findDistanceBetweenTwoLists(firstList: List<Int>, secondList: List<Int>): Int {
        if (firstList.size == secondList.size) {
            var totalDistance = 0

            firstList.sorted().zip(secondList.sorted()) { first, second ->
                totalDistance += abs(first - second)
            }

            return totalDistance
        }
        return -1
    }

    fun getListsFromFile() : Pair<List<Int>, List<Int>> {
        val readFile = File("app\\src\\main\\java\\com\\example\\aoc2024\\day1\\Input.txt").readLines()
        val firstList: MutableList<Int> = mutableListOf()
        val secondList: MutableList<Int> = mutableListOf()
        readFile.forEach {
            val line = it.split("   ")
            firstList.add(line[0].toInt())
            secondList.add(line[1].toInt())
        }
        return Pair(firstList, secondList)
    }

    override fun solution() : Int {
        val lists = getListsFromFile()
        return findDistanceBetweenTwoLists(lists.first, lists.second)
    }
}