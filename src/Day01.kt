import kotlin.math.absoluteValue

fun main() {

    fun extractInputInSeparateList(input: List<String>): List<MutableList<String>> {
        val initialList = listOf<MutableList<String>>(mutableListOf(), mutableListOf())

        val arrayOArray = input.fold(initialList) { acc, next ->
            val strSplit = next.split( """\s+""".toRegex())
            acc[0].add(strSplit[0])
            acc[1].add(strSplit[1])
            acc
        }
        return arrayOArray
    }

    fun part1(input: List<String>): Int {
        val arrayOArray = extractInputInSeparateList(input)
        arrayOArray.first().sort()
        arrayOArray[1].sort()

        val result = arrayOArray[0].foldIndexed(0) { index, acc, next ->
            acc + (next.toInt() - arrayOArray[1][index].toInt()).absoluteValue
        }

        return result
    }

    fun part2(input: List<String>): Int {
        val arrayOArray = extractInputInSeparateList(input)

        val result = arrayOArray[0].fold(0) {acc, next ->
            acc + (next.toInt() * arrayOArray[1].count { it == next })
        }

        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    // Or read a large test input from the `src/Day01_test.txt` file:
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
