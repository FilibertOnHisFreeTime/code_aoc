import java.lang.Integer.max
import kotlin.math.absoluteValue

fun main() {

    fun is_safe(report: List<Int>,with_joker: Boolean): Boolean {
        var index = 0
        var desc_or_asc = ""
        var has_an_error = false;
        while ( index < report.size - 1){
            if (index == 0) {
                 desc_or_asc = if (report[0] - report[1] < 0) {
                    "asc"
                } else {
                    "desc"
                };
            }
            val is_valid = if (desc_or_asc == "asc") {
                (report[index] - report[index + 1]) in -3..-1
            } else if (desc_or_asc == "desc") {
                (report[index] - report[index + 1]) in 1..3
            } else {
                false
            }
             if (!is_valid) {
                 if (with_joker && is_safe(report.filterIndexed { i1, _i -> index != i1 }, false)){
                     break;
                 }
                 else if (with_joker && is_safe(report.filterIndexed { i1, _i -> index + 1 != i1 }, false)){
                     break;
                 }
                 else if (with_joker && index > 0 && is_safe(report.filterIndexed { i1, _i -> index - 1 != i1 }, false)){
                     break;
                 }
                 else {
                     has_an_error = true
                 }
                break;
            } else {
                index ++;
            }
        }
        return !has_an_error;
    }
    fun part1(input: List<String>): Int {
        var desc_or_asc = "";
        val result = input.fold(0) { acc, next ->
            val report = next.split(' ').map { it.toInt() }.toMutableList()
            if (is_safe(report, false)) acc + 1  else acc
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val result = input.fold(0) { acc, next ->
            val report = next.split(' ').map { it.toInt() }.toMutableList()

            if (is_safe(report, true)) acc + 1 else acc
        }
        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
 // Or read a large test input from the `src/Day01_test.txt` file:
      part2(testInput).println()
      check(part2(testInput) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
//    part1(input).println()
    part1(input).println()
    part2(input).println()
}
