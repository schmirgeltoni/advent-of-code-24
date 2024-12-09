package com.example.aoc2024.day2

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.day2.Day2Part1.isReportSafe
import com.example.aoc2024.readFileLines
import com.example.aoc2024.toIntList

object Day2Part2 : AdventOfCodeChallenge {

    private fun List<Int>.bruteForceProblemDampener(): Boolean {
        for (i in this.indices) {
            val mutList = this.toMutableList()
            mutList.removeAt(i)
            if (mutList.isReportSafe()) return true
        }
        return false
    }

    override fun solution(): Int {
        return readFileLines("day2").count {
            val list = it.split(" ").toIntList()
            list.isReportSafe() || list.bruteForceProblemDampener()
        }
    }

    override fun test(): Int {
        return Day2Part1.testReports.count {
            it.isReportSafe() || it.bruteForceProblemDampener()
        }
    }
}