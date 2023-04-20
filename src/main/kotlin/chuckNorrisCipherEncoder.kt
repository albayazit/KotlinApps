fun main() {
    println("Input string:")
    val word = readln()
    val digits = listOf<Char>('1', '2', '3', '4', '5', '6', '7', '8', '9', '0')
    println("The result:")
    for(ch in word) {
        var binary = ""
//        binary = String.format("%7S", 0,  Integer.toBinaryString(ch.toInt()))
        println("$ch = $binary")
    }
}