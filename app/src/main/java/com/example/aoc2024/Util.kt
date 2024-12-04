package com.example.aoc2024

import java.io.File

/**
 * Returns the lines of Input.txt from the given package
 */
fun readFileLines(packageName: String): List<String> =
    File("app\\src\\main\\java\\com\\example\\aoc2024\\$packageName\\Input.txt").readLines()

/**
 *
 */
fun List<String>.toListOfListOfChars(): List<List<Char>> = this.map { it.toList() }

fun List<Char>.convertToString() : String {
    var ret = ""
    this.forEach {
        ret += it
    }
    return ret
}

fun List<List<Char>>.getColumnsAsStrings() : List<String> {
    val ret = mutableListOf<String>()
    for (i in this.indices){
        var currentString = ""
        for (j in this[0].indices){
            currentString += this[j][i]
        }
        ret.add(currentString)
    }
    return ret
}

fun List<String>.extractDiagonals(minLength: Int): List<String> {
    val nRows = this.size
    val nCols = this[0].length
    val ret = mutableListOf<String>()

    // Extract top-left to bottom-right (\) diagonals
    for (start in 0 until nCols + nRows - 1) {
        val diagonal = StringBuilder()
        for (row in 0 until nRows) {
            val col = start - row
            if (col in 0 until nCols) {
                diagonal.append(this[row][col])
            }
        }
        if (diagonal.length >= minLength) {
            ret.add(diagonal.toString())
        }
    }

    // Extract top-right to bottom-left (/) diagonals
    for (start in -(nRows - 1) until nCols) {
        val diagonal = StringBuilder()
        for (row in 0 until nRows) {
            val col = start + row
            if (col in 0 until nCols) {
                diagonal.append(this[row][col])
            }
        }
        if (diagonal.length >= minLength) {
            ret.add(diagonal.toString())
        }
    }

    return ret
}

fun List<List<Char>>.getRowsAsStrings() : List<String> {
    val ret = mutableListOf<String>()
    for (i in 3..this.size -4){
        var currentString = ""
        for (j in this[0].indices){
            currentString += this[j][i]
        }
        ret.add(currentString)
    }
    return ret
}

/**
 * Returns the content of Input.txt of the given package as one string
 */
fun readWholeFile(packageName: String): String =
    File("app\\src\\main\\java\\com\\example\\aoc2024\\$packageName\\Input.txt").readText()

/**
 * Returns a map with the keys being unique elements of the [list] and the values being the amount of times it occurs
 */
fun <T> countOccurrences(list: List<T>): Map<T, Int> = list.groupingBy { it }.eachCount()

/**
 * Converts a list of strings to a list of integers
 */
fun List<String>.toIntList(): List<Int> = this.map { it.toInt() }
