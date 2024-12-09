package com.example.aoc2024.day9

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.readWholeFile

object Day9Part1 : AdventOfCodeChallenge {

    val testData = "2333133121414131402"

    private fun List<Int?>.printBlocks() : List<Int?> {
        this.forEach { print(it ?: ".") }
        println()
        return this
    }

    private fun MutableList<Int?>.compactFileSystem(): List<Int?> {
        var indexL = 0
        var indexR = this.size - 1
        while (indexL < indexR) {
            // if there is an empty block to the left
            if (this[indexL] == null) {
                // and there is a file on the right
                if (this[indexR] != null) {
                    this[indexL] = this[indexR]
                    this[indexR] = null
                }
                else
                    indexR--
            }
            else
                indexL++
        }
        return this//.filterNotNull()
    }

    private fun String.toFileSystemWithEmptyBlocks(): MutableList<Int?> {
        val file = mutableListOf<Int?>()
        for (i in this.indices) {
            if (i % 2 == 0) {
                repeat(this[i].digitToInt()) {
                    file.add(i / 2)
                }
            } else {
                repeat(this[i].digitToInt()) {
                    file.add(null)
                }
            }
        }
        return file
    }

    private fun List<Int?>.fileSystemCheckSum() : Long {
        var sum = 0L
        for (i in indices){
            sum += if (this[i] != null) i * this[i]!! else 0
        }
        return sum
    }

    override fun runWithRealData(): Any {
        val fileSystem = readWholeFile("day9").toFileSystemWithEmptyBlocks().compactFileSystem()
        return fileSystem.fileSystemCheckSum()
    }

    override fun runWithExampleData(): Any {
        val fileSystem = testData.toFileSystemWithEmptyBlocks()
        return fileSystem.compactFileSystem().fileSystemCheckSum()
    }
}