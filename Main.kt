package converter

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
                convertTo(input, source)
            } else { input } // convertFromSourceToDecimal
            val result = convertFrom(decimalNumber.toInt(), target)
            println("Conversion result: $result")
        }
    } while (input != "/back")
}

private fun convertFrom(number: Int, radix: Int): String {
    val listOfRemainders = mutableListOf<String>()
    var quotient = number
    do {
        listOfRemainders.add(0, if (radix <= 10) "${quotient % radix}" else matchDecimalToLetter(quotient % 16))
        quotient /= radix
    } while (quotient > 0)
    return listOfRemainders.joinToString("")
}

private fun convertTo(number: String, radix: Int): String {
    val convertedNumber = number.reversed()
    var sum: Long = 0
    for (i in 0..convertedNumber.length - 1) {
        val multiplier: String = if (radix <= 10) convertedNumber[i].toString() else transformLetterToDecimal(convertedNumber[i])
        sum += multiplier.toLong() * radix.toDouble().pow(i).toLong()
    }
    return "$sum"
}

private fun transformLetterToDecimal(number: Char): String {
    return (when (number) {
        in 'a'..'z' -> (number + 35) - 'z'
        else -> number
    }).toString()
}

private fun matchDecimalToLetter(number: Int): String {
    return when (number) {
        in 11..36 -> "${'a' + (number - 11)}"
        else -> "$number"
    }
}
