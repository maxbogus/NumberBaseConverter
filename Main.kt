package converter

import kotlin.math.pow

fun main() {
    do {
        println("Do you want to convert /from decimal or /to decimal? (To quit type /exit)")
        val input = readLine()!!
        when (input) {
            "/from" -> {
                println("Enter number in decimal system:")
                val number = readLine()!!.toInt()
                println("Enter target base:")
                val radix = readLine()!!.toInt()
                println("Conversion result: ${convertDecimal(number, radix)}")
            }
            "/to" -> {
                println("Enter source number:")
                val number = readLine()!!
                println("Enter source base:")
                val base = readLine()!!.toInt()
                println("Conversion to decimal result: ${convertToDecimal(number, base)}")
            }
        }
    } while (input != "/exit")
}

private fun matchDecimalToHexadecimal(number: Int): String {
    return when (number) {
        15 -> "F"
        14 -> "E"
        13 -> "D"
        12 -> "C"
        11 -> "B"
        10 -> "A"
        else -> "$number"
    }
}

private fun convertDecimal(number: Int, radix: Int): String {
    val listOfRemainders = mutableListOf<String>()
    var quotient = number
    do {
        listOfRemainders.add(0, if (radix != 16) "${quotient % radix}" else matchDecimalToHexadecimal(quotient % 16))
        quotient /= radix
    } while (quotient > 0)
    return listOfRemainders.joinToString("")
}

private fun convertToDecimal(number: String, radix: Int): String {
    return when (radix) {
        8 -> convertFrom(number, radix)
        2 -> convertFrom(number, radix)
        else -> convertFrom(number, radix)
    }
}

private fun matchHexadecimalToOctal(number: Char): String {
    return (when (number) {
        'f' -> 15
        'e' -> 14
        'd' -> 13
        'c' -> 12
        'b' -> 11
        'a' -> 10
        else -> number
    }).toString()
}

private fun convertFrom(number: String, radix: Int): String {
    val convertedNumber = number.reversed()
    var sum: Long = 0
    for (i in 0..convertedNumber.length - 1) {
        val multiplier: String = if (radix != 16) convertedNumber[i].toString() else matchHexadecimalToOctal(convertedNumber[i])
        sum += multiplier.toLong() * radix.toDouble().pow(i).toLong()
    }
    return "$sum"
}
