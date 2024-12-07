package com.example.aoc2024.day7

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.readFileLines

object Day7Part1 : AdventOfCodeChallenge {

    val testData = """
        190: 10 19
        3267: 81 40 27
        83: 17 5
        156: 15 6
        7290: 6 8 6 15
        161011: 16 10 13
        192: 17 8 14
        21037: 9 7 18 13
        292: 11 6 16 20
    """.trimIndent().split("\n")

    private fun isEquationPossible(result: Long, numbers: List<Long>): Boolean {
        if (numbers.isEmpty()) return false
        if (numbers.size == 1) return numbers[0] == result

        fun evaluate(index: Int, current: Long): Boolean {
            if (index == numbers.size) return current == result
            if (evaluate(index + 1, current + numbers[index])) return true
            if (evaluate(index + 1, current * numbers[index])) return true

            return false
        }

        // Start evaluation from the first number
        return evaluate(1, numbers[0])
    }

    private fun List<String>.sumUpCorrectEquations(): Long {
        var res = 0L
        this.forEach {
            val (result, numbers) = it.split(": ")
            if (isEquationPossible(result.toLong(),numbers.split(" ").map(String::toLong)))
                res += result.toLong()
        }
        return res
    }

    override fun runWithRealData(): Long {
        return readFileLines("day7").sumUpCorrectEquations()
    }

    override fun runWithExampleData(): Long {
        return testData.sumUpCorrectEquations()
    }
}
