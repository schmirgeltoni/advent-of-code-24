@file:Suppress("SpellCheckingInspection", "MayBeConstant")

package com.example.aoc2024.day3

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.readWholeFile
import com.example.aoc2024.toIntList

object Day3Part2: AdventOfCodeChallenge {

    private val testData = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"

    private val regexPattern = Regex("""mul\([1-9]\d{0,2},[1-9]\d{0,2}\)|do\(\)|don't\(\)""")

    private fun calculateNumberFromCorruptedData(data : String) : Int {
        var sum = 0
        var canCalculate = true
        regexPattern.findAll(data).map { it.value }.toList().forEach{ line ->
            when {
                line == "do()" -> canCalculate = true
                line == "don't()" -> canCalculate = false
                line.startsWith("mul") -> {
                    if (canCalculate) {
                        val numbers = Day3Part1.regexNumbers.findAll(line).map { it.value }.toList().toIntList()
                        sum += numbers[0] * numbers[1]
                    }
                }
                else -> throw IllegalArgumentException("$line was not recognized")
            }
        }
        return sum
    }

    override fun solution(): Int {
        return calculateNumberFromCorruptedData(readWholeFile("day3"))
    }

    override fun runWithExampleData(): Int {
        return calculateNumberFromCorruptedData(testData)
    }
}