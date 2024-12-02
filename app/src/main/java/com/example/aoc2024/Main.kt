package com.example.aoc2024

import com.example.aoc2024.dayOne.Day1Part1

fun main() {
    val dayOneExampleListOne = listOf(3,4,2,1,3,3)
    val dayOneExampleListTwo = listOf(4,3,5,3,9,3)

    println(Day1Part1.findDistanceBetweenTwoLists(dayOneExampleListOne, dayOneExampleListTwo))
    println(Day1Part1.solution())
}
