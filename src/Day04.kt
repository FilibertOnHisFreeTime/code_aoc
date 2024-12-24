import kotlin.math.absoluteValue

fun main() {

    fun get_val(input: List<String>, position: Pair<Int, Int>): Char {
        return input[position.first][position.second]
    }

    fun get_next_cell_position(direction: String, position: Pair<Int, Int>): Pair<Int, Int> {
        val (x, y) = position
        return when (direction) {
            "left" -> Pair(x, y - 1)
            "right" -> Pair(x, y + 1)
            "up" -> Pair(x - 1, y)
            "down" -> Pair(x + 1, y)
            "down_left" -> Pair(x + 1, y - 1)
            "down_right" -> Pair(x + 1, y + 1)
            "up_left" -> Pair(x - 1, y - 1)
            "up_right" -> Pair(x - 1, y + 1)
            else -> position
        }
    }

    fun check_neighbour(
        input: List<String>,
        direction: String,
        current_letter: Char,
        position: Pair<Int, Int>
    ): Boolean {
        val max_x = input.size - 1
        val max_y = input[0].length - 1

        val next_postion = get_next_cell_position(direction, position)

        if (next_postion.first < 0 || next_postion.second < 0) return false
        if (next_postion.first > max_x || next_postion.second > max_y) return false

        val next_val = get_val(input, next_postion)
        return when (current_letter) {
            'X' -> if (next_val == 'M') check_neighbour(input, direction, next_val, next_postion) else false
            'M' -> if (next_val == 'A') check_neighbour(input, direction, next_val, next_postion) else false
            'A' -> next_val == 'S'
            else -> false
        }
    }


    fun check_neighbour_part_2(
        input: List<String>,
        position: Pair<Int, Int>
    ): Boolean {
        val max_x = input.size - 1
        val max_y = input[0].length - 1

        val up_right_position = get_next_cell_position("up_right", position)
        val up_left_position = get_next_cell_position("up_left", position)
        val down_right_position = get_next_cell_position("down_right", position)
        val down_left_position = get_next_cell_position("down_left", position)

        if (up_right_position.first < 0 || up_left_position.second < 0) return false
        if (down_left_position.first > max_x || down_right_position.first > max_x) return false
        if (up_right_position.second > max_y || down_right_position.second > max_y) return false

        val diag_1 = listOf(
            get_val(input, up_right_position),
            get_val(input, down_left_position),
        )
        val diag_2 = listOf(
            get_val(input, down_right_position),
            get_val(input, up_left_position)
        )


        return diag_1.contains('M') && diag_1.contains('S') && diag_2.contains('M') && diag_2.contains('S')
    }

    fun part1(input: List<String>): Int {
        val max_x = input.size - 1
        val max_y = input[0].length - 1
        var total = 0

        for (x in 0..max_x) {
            for (y in 0..max_y) {
                if (input[x][y] == 'X') {
                    if (check_neighbour(input, "left", 'X', Pair(x, y))) total++
                    if (check_neighbour(input, "right", 'X', Pair(x, y))) total++
                    if (check_neighbour(input, "up", 'X', Pair(x, y))) total++
                    if (check_neighbour(input, "down", 'X', Pair(x, y))) total++
                    if (check_neighbour(input, "down_left", 'X', Pair(x, y))) total++
                    if (check_neighbour(input, "down_right", 'X', Pair(x, y))) total++
                    if (check_neighbour(input, "up_left", 'X', Pair(x, y))) total++
                    if (check_neighbour(input, "up_right", 'X', Pair(x, y))) total++
                }
            }
        }
        return total
    }

    fun part2(input: List<String>): Int {
        val max_x = input.size - 1
        val max_y = input[0].length - 1
        var total = 0

        for (x in 0..max_x) {
            for (y in 0..max_y) {
                if (input[x][y] == 'A') {
                    if (check_neighbour_part_2(input, Pair(x, y))) total++
                }
            }
        }
        return total
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    println(part1(testInput))
    check(part1(testInput) == 18)
    // Or read a large test input from the `src/Day01_test.txt` file:
    check(part2(testInput) == 9)
    println(part2(testInput))

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
