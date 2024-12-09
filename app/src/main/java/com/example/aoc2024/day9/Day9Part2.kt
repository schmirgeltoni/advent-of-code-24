package com.example.aoc2024.day9

import com.example.aoc2024.AdventOfCodeChallenge
import com.example.aoc2024.day9.Day9Part1.fileSystemCheckSum
import com.example.aoc2024.day9.Day9Part1.getBlockView
import com.example.aoc2024.day9.Day9Part1.testData
import com.example.aoc2024.day9.Day9Part1.toFileSystem
import com.example.aoc2024.readWholeFile

object Day9Part2 : AdventOfCodeChallenge {

    private fun MutableList<Int?>.compactFileSystem(): List<Int?> {
        val movedFileIDs = mutableSetOf<Int>()
        var indexR = this.size - 1
        while (indexR > 0) {
            // some file is found on the right that hasn't been moved yet
            if (this[indexR] != null && !(movedFileIDs.contains(this[indexR]!!))) {
                val fileID = this[indexR]!!
                val fileSize = getFileSizeFromTheLeft(indexR, fileID)
                val freeSpanOfBlocks = findEmptyBlockSpan(0, indexR, fileSize)
                // if a span of empty blocks has been found
                if (freeSpanOfBlocks != null) {
                    this.moveFile(freeSpanOfBlocks, (indexR - fileSize + 1)..indexR, fileID)
                    indexR--
                    movedFileIDs.add(fileID)
                } else {
                    indexR -= fileSize
                }
            } else {
                indexR--
            }
        }
        return this
    }

    private fun MutableList<Int?>.moveFile(spaceToFill: IntRange, spaceToDelete: IntRange, fileID: Int){
        // fill open span with file
        for (i in spaceToFill) this[i] = fileID
        // delete old file
        for (i in spaceToDelete) this[i] = null
    }

    @Suppress("ControlFlowWithEmptyBody")
    private fun List<Int?>.getFileSizeFromTheLeft(startingPoint: Int, fileID: Int): Int {
        var i = 0
        if (fileID == 0) return 10
        while (this[startingPoint - ++i] == fileID) { }
        return i
    }

    private fun <T> List<T?>.findEmptyBlockSpan(
        startingPoint: Int,
        endPoint: Int,
        lengthOfSpan: Int
    ): IntRange? {
        var currentSpan = 0
        for (i in startingPoint..endPoint) {
            if (this[i] == null) {
                if (lengthOfSpan == 1) return (i..i)
                if (++currentSpan == lengthOfSpan) return (i - currentSpan + 1)..i
            } else {
                currentSpan = 0
            }
        }
        return null
    }

    override fun solution(): Any {
        val fileSystem = readWholeFile("day9").toFileSystem().compactFileSystem()
        return fileSystem.fileSystemCheckSum().also { println(fileSystem.getBlockView()) }
    }

    override fun test(): Any {
        val fileSystem = testData.toFileSystem()
        return fileSystem.compactFileSystem().fileSystemCheckSum()
    }
}