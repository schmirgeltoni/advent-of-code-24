package com.example.aoc2024.day2

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.day2.Day2Part1.isReportSafe
import com.example.aoc2024.readFile
import com.example.aoc2024.toIntList

object Day2Part2 : AdventOfCodeChallenge {

    override val name: String = "Day 2 Part 2"

    private fun List<Int>.bruteForceProblemDampener(): Boolean {
        for (i in this.indices) {
            val mutList = this.toMutableList()
            mutList.removeAt(i)
            if (mutList.isReportSafe()) return true
        }
        return false
    }

    override fun solution(): Int {
        return readFile("app\\src\\main\\java\\com\\example\\aoc2024\\day2\\Input.txt").count {
            val list = it.split(" ").toIntList()
            list.isReportSafe() || list.bruteForceProblemDampener()
        }
    }

    override fun runWithExampleData(): Int {
        return Day2Part1.testReports.count {
            it.isReportSafe() || it.bruteForceProblemDampener()
        }
    }
}