@file:Suppress("SpellCheckingInspection", "MayBeConstant")

package com.example.aoc2024.day3

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.readWholeFile
import com.example.aoc2024.toIntList

object Day3Part2: AdventOfCodeChallenge {

    private val testData = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"

    private val regexPattern = Regex("""mul\([1-9]\d{0,2},[1-9]\d{0,2}\)|do\(\)|don't\(\)""")

    private fun calculateNumberFromCorruptedData(data : String) : Int {
        val processedData = regexPattern.findAll(data).map { it.value }.toList()
        var sum = 0
        var canCalculate = true
        for (i in processedData.indices){
            val current = processedData[i]
            when {
                current == "do()" -> canCalculate = true
                current == "don't()" -> canCalculate = false
                current.startsWith("mul") -> {
                    if (canCalculate){
                        val numbers = Day3Part1.regexNumbers.findAll(current).map { it.value }.toList().toIntList()
                        sum += numbers[0] * numbers[1]
                    }
                }
                else -> throw IllegalArgumentException("$current was not recognized")
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