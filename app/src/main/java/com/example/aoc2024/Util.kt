package com.example.aoc2024

import java.io.File

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

fun <T> Collection<Collection<T>>.logMatrix(): Collection<Collection<T>> {
    this.forEach {
        it.forEach { element ->
            print(element.toString())
        }
        println()
    }
    return this
}
