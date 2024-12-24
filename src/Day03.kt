import kotlin.math.absoluteValue

fun main() {

    fun part1(input: List<String>): Int {
        var reggex = Regex("""mul\(\d{1,3},\d{1,3}\)""")
        val number_tomultiply = Regex("""mul\((\d{1,3}),(\d{1,3})\)""")

        var total = input.fold(0) { result, input_string ->
            val temp_result = reggex.findAll(input_string).fold(0) { acc, value ->
                val extracted_number = number_tomultiply.find(value.value)
                val a = extracted_number?.groups?.get(1)?.value?.toInt()
                val b = extracted_number?.groups?.get(2)?.value?.toInt()
                if (a == null || b == null) return acc
                acc + a * b
            }
            result + temp_result
        }
        return total
    }

    fun part2(input: List<String>): Int {
        var reggex = Regex("""mul\(\d{1,3},\d{1,3}\)|don't\(\)|do\(\)""")

        val number_tomultiply = Regex("""mul\((\d{1,3}),(\d{1,3})\)""")
        var is_open = true

        var total = input.fold(0) { result, input_string ->
            val temp_result = reggex.findAll(input_string).fold(0) { acc, value ->
                var to_add = 0

                if (value.value == "don't()"){
                    is_open= false
                }
                else if (value.value == "do()") {
                    is_open=true
                }
                else if (is_open){
                    val extracted_number = number_tomultiply.find(value.value)
                    val a = extracted_number?.groups?.get(1)?.value?.toInt()
                    val b = extracted_number?.groups?.get(2)?.value?.toInt()
                    if (a == null || b == null) return acc
                    to_add = a * b
                }
                acc + to_add
            }
            result + temp_result
        }
        return total
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    part1(testInput).println()
    check(part1(testInput) == 161)
    part2(testInput).println()
    check(part2(testInput) == 48)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
