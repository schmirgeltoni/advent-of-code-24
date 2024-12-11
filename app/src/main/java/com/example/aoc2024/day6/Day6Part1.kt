package com.example.aoc2024.day6

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.Direction
import com.example.aoc2024.readFileLines

@Suppress("ControlFlowWithEmptyBody")
object Day6Part1 : AdventOfCodeChallenge {

    enum class Position(val char: Char) {
        Guard('^'), Obstacle('#'), Free('.'), Visited('X')
    }



    class Guard(var x: Int, var y: Int, var facing: Direction) {
        override fun toString() = """
            x: $x
            y: $y
            facing: $facing
        """.trimIndent()

        fun changeDirection() {
            facing = when (facing) {
                Direction.Up -> Direction.Right
                Direction.Right -> Direction.Down
                Direction.Down -> Direction.Left
                Direction.Left -> Direction.Up
            }
        }

        fun takeStep() {
            when (facing) {
                Direction.Up -> y -= 1
                Direction.Down -> y += 1
                Direction.Left -> x -= 1
                Direction.Right -> x += 1
            }
        }
    }

    val testData = listOf(
        "....#.....",
        ".........#",
        "..........",
        "..#.......",
        ".......#..",
        "..........",
        ".#..^.....",
        "........#.",
        "#.........",
        "......#..."
    )

    fun List<String>.mapInputToModel(): MutableList<MutableList<Position>> {
        return map {
            it.toCharArray()
        }.map {
            it.map { char ->
                when (char) {
                    '.' -> Position.Free
                    '#' -> Position.Obstacle
                    '^' -> Position.Guard
                    else -> throw IllegalArgumentException("Unknown character")
                }
            }.toMutableList()
        }.toMutableList()
    }

    override fun solution(): Int {
        val model = readFileLines("day6").mapInputToModel()
        val guard = model.findGuard()
        while (!model.nextStep(guard)) {
        }

        return model.flatten().count { it == Position.Visited || it == Position.Guard }
    }

    override fun test(): Int {
        val model = testData.mapInputToModel()
        val guard = model.findGuard()
        while (!model.nextStep(guard)) {
        }

        return model.flatten().count { it == Position.Visited || it == Position.Guard }
    }

    /**
     * Makes the next step of the guard according to logic and updates [this] and [guard] accordingly
     * @return true if the guard has reached an edge, false if not
     */
    fun MutableList<MutableList<Position>>.nextStep(guard: Guard): Boolean {
        val x = guard.x
        val y = guard.y
        if (x !in 1..<size - 1 || y !in 1..<this[0].size - 1) return true
        when (guard.facing) {
            Direction.Up -> {
                when (this[y - 1][x]) {
                    Position.Guard -> throw Exception()
                    Position.Obstacle -> {
                        guard.changeDirection()
                        return nextStep(guard)
                    }
                    Position.Free, Position.Visited -> {
                        guard.takeStep()
                        this[y][x] = Position.Visited
                        this[y - 1][x] = Position.Guard
                    }
                }
            }

            Direction.Down -> {
                when (this[y + 1][x]) {
                    Position.Guard -> throw Exception()
                    Position.Obstacle -> {
                        guard.changeDirection()
                        return nextStep(guard)
                    }
                    Position.Free, Position.Visited -> {
                        guard.takeStep()
                        this[y][x] = Position.Visited
                        this[y + 1][x] = Position.Guard
                    }
                }
            }

            Direction.Left -> {
                when (this[y][x - 1]) {
                    Position.Guard -> throw Exception()
                    Position.Obstacle -> {
                        guard.changeDirection()
                        return nextStep(guard)
                    }
                    Position.Free, Position.Visited -> {
                        guard.takeStep()
                        this[y][x] = Position.Visited
                        this[y][x - 1] = Position.Guard
                    }
                }
            }

            Direction.Right -> {
                when (this[y][x + 1]) {
                    Position.Guard -> throw Exception()
                    Position.Obstacle -> {
                        guard.changeDirection()
                        return nextStep(guard)
                    }
                    Position.Free, Position.Visited -> {
                        guard.takeStep()
                        this[y][x] = Position.Visited
                        this[y][x + 1] = Position.Guard
                    }
                }
            }
        }
        return false
    }

    fun MutableList<MutableList<Position>>.findGuard(): Guard {
        val ret = Guard(0, 0, Direction.Up)
        for (i in indices) {
            for (j in this[0].indices) {
                if (this[i][j] == Position.Guard) {
                    ret.x = j
                    ret.y = i
                }
            }
        }
        return ret
    }

    fun MutableList<MutableList<Position>>.logModel(): MutableList<MutableList<Position>> {
        forEach {
            it.forEach { position: Position ->
                print(position.char)
            }
            println()
        }
        return this
    }
}