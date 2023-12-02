@JvmInline
value class Color(val value: String)

data class Cube(val color: Color, val count: Int)

fun main() {
    fun String.asCube(): Cube {
        val (count, color) = this.split(" ", limit = 2)
        return Cube(Color(color), count.toInt())
    }

    val maxCountsPerColor = mapOf(
        Color("green") to 13,
        Color("red") to 12,
        Color("blue") to 14
    )

    fun part1(input: List<String>): Int = input.sumOf { line ->
        val (lineId, cubes) = line.split(":", limit = 2)
        val counts = cubes.trim().split("; ").flatMap { it.split(", ").map(String::asCube) }
            .groupBy { it.color }.mapValues { (_, cubes) -> cubes.sumOf(Cube::count) }

        if (maxCountsPerColor.all { (color, maxCount) -> (counts[color] ?: 0) <= maxCount }) {
            lineId.replaceFirst("Game ", "").toInt()
        } else 0
    }

    fun part2(input: List<String>): Int = input.sumOf { line ->
        line.split(":", limit = 2).let { (_, cubes) ->
            cubes.trim().split("; ").flatMap { it.split(", ").map(String::asCube) }
                .groupingBy { it.color }.fold(1) { acc, cube -> maxOf(acc, cube.count) }
                .values.reduce(Int::times)
        }
    }

    with(readInput("Day02_test")) {
        check(part1(this) == 8)
        check(part2(this) == 2286)
    }
    with(readInput("Day02")) {
        println(part1(this))
        println(part2(this))
    }
}
