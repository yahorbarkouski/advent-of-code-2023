val wordToDigit = mapOf(
    "one" to "1", "two" to "2", "three" to "3", "four" to "4",
    "five" to "5", "six" to "6", "seven" to "7", "eight" to "8", "nine" to "9"
)

fun main() {
    val sumA = readInput("Day01").sumOf { line ->
        val digits = line.filter { it.isDigit() }
        if (digits.length >= 2) "${digits.first()}${digits.last()}".toInt() else 0
    }
    println("$sumA")

    val sumB = readInput("Day01").sumOf { string ->
        val (first, second) = string.findDigits()
        "$first$second".toInt()
    }
    println("$sumB")
}

private fun String.findDigits(): Pair<String, String> {
    val mapped = this.split("\\s+".toRegex())
        .mapNotNull { wordToDigit[it] ?: if (it.length == 1 && it.first().isDigit()) it else null }
        .take(2)

    return if (mapped.size == 2) mapped[0] to mapped[1] else "0" to "0"
}
