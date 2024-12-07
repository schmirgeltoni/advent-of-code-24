package com.example.aoc2024.day7

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.day7.Day7Part1.sumUpCorrectEquations
import com.example.aoc2024.day7.Day7Part1.testData
import com.example.aoc2024.readFileLines

object Day7Part2 : AdventOfCodeChallenge {

    private val isEquationPossible = { result: Long, numbers: List<Long> ->
        fun evaluate(index: Int, current: Long): Boolean {
            if (index == numbers.size) return current == result
            if (evaluate(index + 1, current + numbers[index])) return true
            if (evaluate(index + 1, current * numbers[index])) return true
            if (evaluate(
                    index + 1,
                    (current.toString() + numbers[index].toString()).toLong()
                )
            ) return true

            return false
        }
        evaluate(1, numbers[0])
    }

    override fun runWithRealData(): Any {
        return readFileLines("day7").sumUpCorrectEquations(isEquationPossible)
    }

    override fun runWithExampleData(): Any {
        return testData.sumUpCorrectEquations(isEquationPossible)
    }
}