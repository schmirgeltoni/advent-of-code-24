package com.example.aoc2024

import java.io.File

fun readFile(path: String): List<String> = File(path).readLines()

fun List<String>.toIntList(): List<Int> {
    val ret: MutableList<Int> = mutableListOf()
    this.forEach {
        try {
            ret.add(it.toInt())
        } catch (e: NumberFormatException) {
            println(e.message)
        }
    }
    return ret
}