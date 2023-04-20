fun main() {
    println("Input string:")
    val word = readln()
    val digits = listOf<Char>('1', '2', '3', '4', '5', '6', '7', '8', '9', '0')
    println("The result:")
    for(ch in word) {
        var binary = ""
        binary = String.format("%7S", Integer.toBinaryString(ch.toInt())).replace(' ', '0')
        println("$ch = $binary")
    }
}