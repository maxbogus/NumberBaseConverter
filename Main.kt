package converter

fun main() {
    println("Enter number in decimal system:")
    val number = readLine()!!.toInt()
    println("Enter target base:")
    val radix = readLine()!!.toInt()
    println("Conversion result: ${convertDecimalToRadix(number, radix)}")
}

private fun convertDecimalToBinary(number: Int): String {
    val listOfRemainders = mutableListOf<Int>()
    var quotient = number
    do {
        listOfRemainders.add(0,quotient % 2)
        quotient /= 2
    } while (quotient > 0)
    return listOfRemainders.joinToString("")
}

private fun convertDecimalToOctal(number: Int): String {
    val listOfRemainders = mutableListOf<Int>()
    var quotient = number
    do {
        listOfRemainders.add(0,quotient % 8)
        quotient /= 8
    } while (quotient > 0)
    return listOfRemainders.joinToString("")
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

private fun convertDecimalToHexadecimal(number: Int): String {
    val listOfRemainders = mutableListOf<String>()
    var quotient = number
    do {
        listOfRemainders.add(0, matchDecimalToHexadecimal(quotient % 16))
        quotient /= 16
    } while (quotient > 0)
    return listOfRemainders.joinToString("")
}

private fun convertDecimalToRadix(number: Int, radix: Int): String {
    return when (radix) {
        8 -> convertDecimalToOctal(number)
        2 -> convertDecimalToBinary(number)
        else -> convertDecimalToHexadecimal(number)
    }
}
