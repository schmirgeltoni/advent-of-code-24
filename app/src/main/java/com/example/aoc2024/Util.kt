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
 * Prints [T] and returns it
 *
 * Prepends [prefix] to [T] and appends [postfix]
 */
fun <T> T.log(prefix: String? = null, postfix: String? = null): T =
    this.also { println("${prefix ?: ""}$this${postfix ?: ""}") }

/**
 * Prints the 2D collection along with indices along the x and y coordinate
 * @param replacements A map of elements of type [T], where the given value [String]
 * should be printed instead of the element itself
 */
fun <T> Collection<Collection<T>>.logMatrix(replacements: Map<T, String>? = null): Collection<Collection<T>> {
    print("  ")
    repeat(size) { print(it % 10) }
    println()
    var row = 0
    forEach {
        print("${row++ % 10} ")
        it.forEach { element ->
            print(replacements?.get(element) ?: element.toString())
        }
        println()
    }
    return this
}

fun <T> List<List<T>>.above(y: Int, x: Int): T? = if (y != 0) this[y - 1][x] else null
fun <T> List<List<T>>.below(y: Int, x: Int): T? = if (y != size - 1) this[y + 1][x] else null
fun <T> List<List<T>>.leftOf(y: Int, x: Int): T? = if (x != 0) this[y][x - 1] else null
fun <T> List<List<T>>.rightOf(y: Int, x: Int): T? = if (x != this[y].size - 1) this[y][x + 1] else null