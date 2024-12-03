@file:Suppress("SpellCheckingInspection", "MayBeConstant")

package com.example.aoc2024.day3

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.toIntList
import java.io.File

object Day3Part1 : AdventOfCodeChallenge {

    override val name: String = "Day 3 Part 1"

    private val testData = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"

    private val regexPattern = Regex("""mul\([1-9]\d{0,2},[1-9]\d{0,2}\)""")
    val regexNumbers = Regex("""[1-9]\d{0,2}""")

    private fun calculateNumberFromCorruptedData(data: String): Int {
        val numbers = regexNumbers.findAll(regexPattern.findAll(data).map { it.value }.toList().toString()).map { it.value }.toList().toIntList()
        var sum = 0
        for (i in numbers.indices step 2){
            sum += numbers[i] * numbers[i + 1]
        }
        return sum
    }

    override fun solution(): Int {
        return calculateNumberFromCorruptedData(File("app\\src\\main\\java\\com\\example\\aoc2024\\day3\\Input.txt").readText())
    }

    override fun runWithExampleData(): Int {
        return calculateNumberFromCorruptedData(testData)
    }
}