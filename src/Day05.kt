import java.util.Collections

fun main() {

    fun get_rules(input: List<String>): List<Pair<Int, Int>> {

        val index = input.indexOf("")

        return input.subList(0, index).map {
           val array = it.split('|')
            array[0].toInt() to array[1].toInt()
        }
    }

    fun get_updates(input: List<String>): List<List<Int>> {
        val index = input.indexOf("")

        return input.subList(index + 1, input.size).map { it.split(",").map { it.toInt() } }
    }

    fun orderUpdates(rules: List<Pair<Int, Int>>, updates: List<Int>): List<Int> {
        val ordered_list = updates
        updates.forEachIndexed() { index, key ->
            val must_be_after_number = rules.filter { it.first == key}.map {it.second}
            val to_update = updates.subList(0, index).indexOfFirst{must_be_after_number.contains(it)}
            if(to_update != -1){
                Collections.swap(ordered_list,index, to_update)
                orderUpdates(rules, ordered_list)
            }
        }
        return ordered_list
    }

    fun part1(input: List<String>): Int {
        val rules = get_rules(input)
        val updates = get_updates(input)

        return updates.fold(0) { acc, update ->
            var is_valid = true
            update.forEachIndexed() { index, key ->
                val must_be_after_number = rules.filter { it.first == key}.map {it.second}
                if(update.subList(0, index).any{must_be_after_number.contains(it)}) is_valid = false
            }
            if (is_valid) acc + update[(update.size / 2)] else acc
        }
    }

    fun part2(input: List<String>): Int {
        val rules = get_rules(input)
        val updates = get_updates(input)

        return updates.fold(0) { acc, update ->
            var is_valid = true
            var updatesOrdered = listOf<Int>()

            update.forEachIndexed() { index, key ->
                val must_be_after_number = rules.filter { it.first == key}.map {it.second}
                if(update.subList(0, index).any{must_be_after_number.contains(it)})
                    is_valid = false
            }
            if(!is_valid) updatesOrdered = orderUpdates(rules, update)

            if (!is_valid) acc + updatesOrdered[(updatesOrdered.size / 2)] else acc
        }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day05_test")
//    part1(testInput).println()
//    check(part1(testInput) == 143)
    // Or read a large test input from the `src/Day01_test.txt` file:
    check(part2(testInput) == 123)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
