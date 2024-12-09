package com.example.aoc2024.day6

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.day6.Day6Part1.Guard
import com.example.aoc2024.day6.Day6Part1.Position
import com.example.aoc2024.day6.Day6Part1.findGuard
import com.example.aoc2024.day6.Day6Part1.mapInputToModel
import com.example.aoc2024.day6.Day6Part1.nextStep
import com.example.aoc2024.day6.Day6Part1.testData
import com.example.aoc2024.readFileLines

object Day6Part2 : AdventOfCodeChallenge {

    private fun MutableList<MutableList<Position>>.isLoop(guard: Guard): Boolean {
        // letting the loop run from 1 to 6437 will also get you the right result for my input
        // but this should guarantee the right result for every input, it just takes longer
        for (i in 1..this.flatten().count { it == Position.Free } * 3 + 1) {
            if (this.nextStep(guard)) return false
        }
        return true
    }

    private fun MutableList<MutableList<Position>>.testEveryPossibility(): Int {
        var count = 0
        for (i in this.indices) {
            for (j in this[0].indices) {
                if (this[i][j] == Position.Free) {
                    val simulationModel = this.clone()
                    simulationModel[i][j] = Position.Obstacle
                    val guard = simulationModel.findGuard()
                    if (simulationModel.isLoop(guard)) {
                        count++
                    }
                }
            }
        }
        return count
    }

    override fun solution(): Int {
        return readFileLines("day6").mapInputToModel().testEveryPossibility()
    }

    override fun test(): Int {
        return testData.mapInputToModel().testEveryPossibility()
    }

    private fun List<List<Position>>.clone(): MutableList<MutableList<Position>> {
        return this.map {
            it.map { pos ->
                pos
            }.toMutableList()
        }.toMutableList()
    }
}