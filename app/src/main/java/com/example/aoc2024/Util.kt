package com.example.aoc2024

import java.io.File

enum class Direction {
    Up, Right, Down, Left
}

data class Point(val x: Int, val y: Int) {
    override fun toString(): String = "($y|$x)"
}


/**
 * Returns the lines of Input.txt from the given package
 */
fun readFileLines(packageName: String): List<String> =
    File("app\\src\\main\\java\\com\\example\\aoc2024\\$packageName\\Input.txt").readLines()

fun List<String>.getColumnsAsStrings(): List<String> {
    val ret = mutableListOf<String>()
    for (i in this.indices) {
        var currentString = ""
        for (j in this[0].indices) {
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

/**
 * Prints [this] and returns it
 */
fun <T> T.log(): T = this.also { println(this) }

/**
 * Prints the 2D collection along with indices along the x and y coordinate
 * @param replacements A map of elements of type [T], where the given value [String]
 * should be printed instead of the element itself
 */
fun <T> Collection<Collection<T>>.logMatrix(replacements: Map<T, String>? = null): Collection<Collection<T>> {
    print("  ")
    for (i in indices) print(i % 10)
    println()
    var row = 0
    forEach {
        print("${row++ % 10} ")
        it.forEach { element ->
            if (replacements != null) {
                print(replacements[element] ?: element.toString())
            }
            else
                print(element.toString())
        }
        println()
    }
    return this
}