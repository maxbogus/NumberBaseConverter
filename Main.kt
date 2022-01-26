package converter

import java.math.BigInteger
import kotlin.math.pow

fun main() {
    do {
        println("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
        val input = readLine()!!
        if (input != "/exit") {
            val convertedInput = input.split(" ").map { it.toInt() }.toList()
            val sourceBase = convertedInput[0]
            val targetBase = convertedInput[1]
            handleConversions(sourceBase, targetBase)
        }
    } while (input != "/exit")
}

private fun handleConversions(source: Int, target: Int) {
    do {
        println("Enter number in base $source to convert to base $target (To go back type /back)")
        val input = readLine()!!
        if (input != "/back") {
            val decimalNumber = if (source != 10) {
                convertToDecimal(input, source)
            } else { input.toBigInteger() } // convertFromSourceToDecimal
            val result = convertFromDecimal(decimalNumber, target)
            println("Conversion result: $result")
        }
    } while (input != "/back")
}

private fun convertFromDecimal(number: BigInteger, radix: Int): String {
    val listOfRemainders = mutableListOf<String>()
    var quotient = number
    do {
        listOfRemainders.add(0, if (radix <= 10) "${quotient % radix.toBigInteger()}" else matchDecimalToLetter((quotient % radix.toBigInteger()).toInt()))
        quotient /= radix.toBigInteger()
    } while (quotient > BigInteger.ZERO)
    return listOfRemainders.joinToString("")
}

private fun convertToDecimal(number: String, radix: Int): BigInteger {
    val convertedNumber = number.reversed()
    var sum: BigInteger = BigInteger.ZERO
    for (i in 0..convertedNumber.length - 1) {
        val multiplier: String = if (radix <= 10) convertedNumber[i].toString() else transformLetterToDecimal(convertedNumber[i])
        sum += multiplier.toBigInteger() * radix.toDouble().pow(i).toLong().toBigInteger()
    }
    return sum
}

private fun transformLetterToDecimal(number: Char): String {
    return (when (number) {
        in 'a'..'z' -> (number + 35) - 'z'
        else -> number
    }).toString()
}

private fun matchDecimalToLetter(number: Int): String {
    return when (number) {
        in 10..36 -> "${'a' + (number - 10)}"
        else -> "$number"
    }
}
