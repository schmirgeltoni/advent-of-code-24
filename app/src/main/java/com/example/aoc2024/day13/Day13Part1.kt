package com.example.aoc2024.day13

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.log
import com.example.aoc2024.readWholeFile

object Day13Part1 : AdventOfCodeChallenge {

    @Suppress("PropertyName")
    class Equation(
        val X: Int,
        val Y: Int,
        val xOfA: Int,
        val yOfA: Int,
        val xOfB: Int,
        val yOfB: Int
    ) {
        override fun toString(): String {
            return """
                Button A: X+$xOfA, Y+$yOfA
                Button B: X+$xOfB, Y+$yOfB
                Prize: X=$X, Y=$Y
            """.trimIndent()
        }
    }

    private fun Equation.calculateTokenCost(): Int {
        for (b in 1..100) {
            for (a in 1..100) {
                if (X == a * xOfA + b * xOfB && Y == a * yOfA + b * yOfB)
                    return a * 3 + b
            }
        }

        return 0
    }


    private fun String.toEquation(): Equation {
        val e = this.split("+", ",", "=", "\n", "\r\n")
        return Equation(
            e[9].toInt(),
            e[11].toInt(),
            e[1].toInt(),
            e[3].toInt(),
            e[5].toInt(),
            e[7].toInt()
        )
    }

    val testData = """
        Button A: X+94, Y+34
        Button B: X+22, Y+67
        Prize: X=8400, Y=5400

        Button A: X+26, Y+66
        Button B: X+67, Y+21
        Prize: X=12748, Y=12176

        Button A: X+17, Y+86
        Button B: X+84, Y+37
        Prize: X=7870, Y=6450

        Button A: X+69, Y+23
        Button B: X+27, Y+71
        Prize: X=18641, Y=10279
    """.trimIndent().split("\n\n")

    override fun solution(): Any {
        val clawMachines = readWholeFile("day13").split("\r\n\r\n").map { it.trim() }
        val equations = clawMachines.map { it.toEquation() }
        return equations.sumOf { it.calculateTokenCost() }
    }

    override fun test(): Any {
        return testData.sumOf { it.toEquation().calculateTokenCost() }
    }
}