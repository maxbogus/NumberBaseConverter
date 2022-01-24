package converter

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
                println("Conversion result: ${convertDecimalByTargetBase(number, radix)}")
            }
            "/to" -> {
                println("Enter source number:")
                val number = readLine()!!.toInt()
                println("Enter source base:")
                val base = readLine()!!.toInt()
                println("Conversion to decimal result: ${convertDecimalByTargetBase(number, base)}")
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

private fun convertDecimalByTargetBase(number: Int, radix: Int): String {
    return when (radix) {
        8 -> convertDecimal(number, 8)
        2 -> convertDecimal(number, 2)
        else -> convertDecimal(number, 16)
    }
}
